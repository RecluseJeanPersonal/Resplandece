package app_iglesia.service;

import app_iglesia.entity.Entrada;
import app_iglesia.entity.Taller;
import app_iglesia.entity.Usuario;
import app_iglesia.payload.request.ActualizarEstadoMasivoRequest;
import app_iglesia.payload.request.EntradaRequest;
import app_iglesia.payload.request.GuardarEntradaRequest;
import app_iglesia.payload.request.TallerRequest;
import app_iglesia.payload.response.EntradasResponse;
import app_iglesia.payload.response.TallerResponse;
import app_iglesia.repository.Entrada.EntradaRepository;
import app_iglesia.repository.taller.TallerRepository;
import app_iglesia.repository.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntradaServiceImpl implements EntradaService {

    private final EntradaRepository entradaRepository;
    private final TallerRepository tallerRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public Entrada registrarEntrada(GuardarEntradaRequest request, UUID idUsuario) {
        List<Taller> talleres = tallerRepository.findAllById(request.getTalleresIds());
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Entrada entrada = new Entrada();
        entrada.setNombre(request.getNombre());
        entrada.setApellido(request.getApellido());
        entrada.setEdad(request.getEdad());
        entrada.setTelefono(request.getTelefono());
        entrada.setEstado(request.getEstado());
        entrada.setTalleres(talleres);
        entrada.setCodigoQR(UUID.randomUUID());
        entrada.setUsuario(usuario);
        entrada.setFechaRegistro(LocalDateTime.now());

        return entradaRepository.save(entrada); // Retorna la entrada guardada
    }

    @Override
    public List<Entrada> guardarEntradasMasivas(List<GuardarEntradaRequest> requestList, UUID idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Entrada> entradasCreadas = new ArrayList<>();

        for (GuardarEntradaRequest req : requestList) {
            List<Taller> talleres = tallerRepository.findAllById(req.getTalleresIds());

            Entrada entrada = new Entrada();
            entrada.setNombre(req.getNombre());
            entrada.setApellido(req.getApellido());
            entrada.setEdad(req.getEdad());
            entrada.setFechanac(req.getFechanac()); // ← AQUI SE AGREGA
            entrada.setTelefono(req.getTelefono());
            entrada.setEstado(req.getEstado());
            entrada.setTalleres(talleres);
            entrada.setCodigoQR(UUID.randomUUID());
            entrada.setUsuario(usuario);
            entrada.setFechaRegistro(LocalDateTime.now());

            Entrada entradaGuardada = entradaRepository.save(entrada);
            entradasCreadas.add(entradaGuardada);
        }

        return entradasCreadas;
    }

    @Override
    public void actualizarEstadoEntrada(UUID idEntrada) {
        Entrada entrada = entradaRepository.findById(idEntrada)
                .orElseThrow(() -> new EntityNotFoundException("Entrada no encontrada con ID: " + idEntrada));

        entrada.setEstado("Registrado");
        entradaRepository.save(entrada);
    }

    @Override
    public void actualizarEstadoMasivo(ActualizarEstadoMasivoRequest request) {
        List<Entrada> entradas = entradaRepository.findAllById(request.getIdsEntradas());
        for (Entrada entrada : entradas) {
            entrada.setEstado("Registrado");
        }

        entradaRepository.saveAll(entradas);
    }

    @Override
    public List<EntradaRequest> listarEntradasPorUsuario(UUID idUsuario) {
        List<Entrada> entradas = entradaRepository.findByUsuarioId(idUsuario);

        return entradas.stream().map(entrada -> {
            EntradaRequest dto = new EntradaRequest();
            dto.setId(entrada.getId());
            dto.setNombre(entrada.getNombre());
            dto.setApellido(entrada.getApellido());
            dto.setFechanac(entrada.getFechanac());
            dto.setEdad(entrada.getEdad());
            dto.setTelefono(entrada.getTelefono());
            dto.setEstado(entrada.getEstado());
            dto.setFechaRegistro(entrada.getFechaRegistro());

            dto.setCodigoQR(!"Pendiente".equalsIgnoreCase(entrada.getEstado())
                    ? entrada.getCodigoQR()
                    : null);

            List<TallerRequest> talleres = entrada.getTalleres().stream().map(taller -> {
                TallerRequest t = new TallerRequest();
                t.setId(taller.getId());
                t.setNombre(taller.getNombre());
                t.setDescripcion(taller.getDescripcion());
                t.setHabilitado(taller.getHabilitado());
                t.setBloque(taller.getBloque());
                t.setInicio(taller.getInicio());
                t.setFin(taller.getFin());
                t.setAnio(taller.getAnio());
                return t;
            }).toList();

            dto.setTalleres(talleres);

            return dto;
        }).toList();
    }
    @Override
    public Optional<Entrada> buscarPorId(UUID idEntrada) {
        return entradaRepository.findById(idEntrada);
    }

    @Override
    public List<EntradasResponse> listarTodasEntradas() {
        List<Entrada> entradas = entradaRepository.findAll();
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

    @Override
    public String validarEntradaPorQr(UUID codigoQr) {
        Optional<Entrada> entradaOpt = entradaRepository.findByCodigoQR(codigoQr);

        if (entradaOpt.isEmpty()) {
            return "Entrada no existe";
        }

        Entrada entrada = entradaOpt.get();

        switch (entrada.getEstado()) {
            case "Registrado":
                entrada.setEstado("Validado");
                entradaRepository.save(entrada);
                return "Entrada aceptada";

            case "Validado":
                return "Entrada ya usada";

            default:
                return "Estado de entrada inválido";
        }
    }

}