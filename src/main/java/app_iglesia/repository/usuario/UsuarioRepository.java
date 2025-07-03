package app_iglesia.repository.usuario;

import app_iglesia.entity.Usuario;
import app_iglesia.service.usuario.UsuarioRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>, UsuarioRepositoryCustom {
    // Buscar por username (por autenticación o validación)
    Optional<Usuario> findByUsernameIgnoreCase(String username);

    // Validar si un username ya está en uso
    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByUsername(String username);

    @Query("""
    SELECT DISTINCT u
    FROM Usuario u
    JOIN u.roles r
    WHERE (:username IS NULL OR :username = '' OR LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%')))
      AND (:habilitado IS NULL OR u.habilitado = :habilitado)
      AND (:rolValue IS NULL OR :rolValue = '' OR LOWER(r.value) LIKE LOWER(CONCAT('%', :rolValue, '%')))
    """)
    List<Usuario> buscarUsuariosConFiltros(
        @Param("username") String username,
        @Param("habilitado") Boolean habilitado,
        @Param("rolValue") String rolValue
    );

}
