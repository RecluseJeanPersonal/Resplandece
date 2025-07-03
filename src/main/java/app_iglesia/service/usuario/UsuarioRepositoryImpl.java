package app_iglesia.service.usuario;

import app_iglesia.entity.Rol;
import app_iglesia.entity.Usuario;
import app_iglesia.payload.request.UsuarioSearchRequest;
import app_iglesia.payload.response.UsuarioListarResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UsuarioListarResponse> buscarUsuarios(UsuarioSearchRequest request) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> root = cq.from(Usuario.class);

        List<Predicate> predicates = new ArrayList<>();

        if (request.getNombre() != null && !request.getNombre().isBlank()) {
            predicates.add(cb.like(cb.lower(root.get("nombre")), "%" + request.getNombre().toLowerCase() + "%"));
        }

        if (request.getApellido() != null && !request.getApellido().isBlank()) {
            predicates.add(cb.like(cb.lower(root.get("apellido")), "%" + request.getApellido().toLowerCase() + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        List<Usuario> usuarios = entityManager.createQuery(cq).getResultList();

        return usuarios.stream()
                .map(usuario -> new UsuarioListarResponse(
                        usuario.getId(),
                        usuario.getUsername(),
                        usuario.getNombre(),
                        usuario.getApellido(),
                        usuario.getTelefono(),
                        usuario.getHabilitado(),
                        usuario.getRoles().stream()
                                .map(Rol::getDescripcion)
                                .collect(Collectors.toSet())
                ))
                .collect(Collectors.toList());
    }
}

