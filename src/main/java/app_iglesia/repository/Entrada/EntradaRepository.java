package app_iglesia.repository.Entrada;

import app_iglesia.entity.Entrada;
import app_iglesia.payload.request.ActualizarEstadoMasivoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, UUID> {
    List<Entrada> findByUsuarioId(UUID idUsuario);
    List<Entrada> findAll();
    Optional<Entrada> findByCodigoQR(UUID codigoQR);
}