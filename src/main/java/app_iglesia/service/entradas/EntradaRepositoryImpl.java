package app_iglesia.service.entradas;

import app_iglesia.entity.Entrada;
import app_iglesia.payload.request.EntradaSearchRequest;
import app_iglesia.payload.response.EntradasResponse;
import app_iglesia.payload.response.TallerResponse;
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
public class EntradaRepositoryImpl implements EntradaRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<EntradasResponse> buscarPorFiltros(EntradaSearchRequest filtro) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Entrada> cq = cb.createQuery(Entrada.class);
        Root<Entrada> root = cq.from(Entrada.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filtro.getNombre() != null && !filtro.getNombre().isBlank()) {
            predicates.add(cb.like(cb.lower(root.get("nombre")), "%" + filtro.getNombre().toLowerCase() + "%"));
        }
        if (filtro.getApellido() != null && !filtro.getApellido().isBlank()) {
            predicates.add(cb.like(cb.lower(root.get("apellido")), "%" + filtro.getApellido().toLowerCase() + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        List<Entrada> entradas = entityManager.createQuery(cq).getResultList();

        return entradas.stream().map(entrada -> {
            List<TallerResponse> talleresDTO = entrada.getTalleres().stream()
                    .map(t -> new TallerResponse(t.getId(), t.getNombre(), t.getDescripcion(), t.getBloque(),
                            t.getInicio(), t.getFin(), t.getAnio()))
                    .collect(Collectors.toList());

            return new EntradasResponse(
                    entrada.getId(),
                    entrada.getNombre(),
                    entrada.getApellido(),
                    entrada.getFechanac(),
                    entrada.getEdad(),
                    entrada.getTelefono(),
                    entrada.getEstado(),
                    talleresDTO
            );
        }).collect(Collectors.toList());
    }
}
