package app_iglesia.repository.rol;

import app_iglesia.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RolRepository extends JpaRepository<Rol, UUID> {
    List<Rol> findByHabilitadoTrue(); // Opcional: solo roles habilitados
    Optional<Rol> findByDescripcion(String descripcion);
}
