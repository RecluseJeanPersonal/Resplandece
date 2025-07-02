package app_iglesia.service;

import app_iglesia.entity.Taller;
import app_iglesia.repository.taller.TallerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TallerServiceImpl implements TallerService {

    private final TallerRepository tallerRepository;

    @Override
    public Taller crearTaller(Taller taller) {
        taller.setHabilitado(true); // opcional si siempre quieres que esté activo al crear
        return tallerRepository.save(taller);
    }

    @Override
    public Taller editarTaller(UUID id, Taller tallerActualizado) {
        Taller taller = tallerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Taller no encontrado"));

        taller.setNombre(tallerActualizado.getNombre());
        taller.setDescripcion(tallerActualizado.getDescripcion());
        taller.setHabilitado(tallerActualizado.getHabilitado());
        taller.setBloque(tallerActualizado.getBloque());
        // Nuevos campos:
        taller.setInicio(tallerActualizado.getInicio());
        taller.setFin(tallerActualizado.getFin());
        taller.setAnio(tallerActualizado.getAnio());

        return tallerRepository.save(taller);
    }

    @Override
    public List<Taller> listarTalleres() {
        return tallerRepository.findAll();
    }
}