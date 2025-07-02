package app_iglesia.repository.rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import app_iglesia.entity.Rol;
import app_iglesia.entity.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

    @Repository
    public interface IRolDAO extends JpaRepository<Rol, UUID> {

        @Query("SELECT NEW Rol(r.id, r.descripcion) " +
                "FROM Rol r " +
                "JOIN r.usuarios u " +
                "WHERE u = ?1 " +
                "AND r.habilitado = TRUE")
        Set<Rol> findRolesByUsuario(Usuario usuario);

        @Query("SELECT NEW Rol(r.id, r.habilitado) " +
                "FROM Rol r " +
                "WHERE r.descripcion LIKE ?1")
        Optional<Rol> findRolByDescripcion(String descripcion);

        Optional<Rol> findRolByValue(String value);

        @Query("""
            SELECT r FROM Rol r 
            WHERE (:value IS NULL OR LOWER(r.value) LIKE LOWER(:value))
            AND (:habilitado IS NULL OR r.habilitado = :habilitado)
        """)
            List<Rol> searchRoles(@Param("value") String value, @Param("habilitado") Boolean habilitado);


    }
