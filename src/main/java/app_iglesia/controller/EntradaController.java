package app_iglesia.controller;

import app_iglesia.entity.Entrada;
import app_iglesia.payload.request.ActualizarEstadoMasivoRequest;
import app_iglesia.payload.request.EntradaRequest;
import app_iglesia.payload.request.EntradaSearchRequest;
import app_iglesia.payload.request.GuardarEntradaRequest;
import app_iglesia.payload.response.EntradasResponse;
import app_iglesia.service.entradas.EntradaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/entradas")
@RequiredArgsConstructor
public class EntradaController {

    private final EntradaService entradaService;

    @PostMapping("/registrar/{idUsuario}")
    public ResponseEntity<String> registrarEntrada(
            @RequestBody GuardarEntradaRequest request,
            @PathVariable UUID idUsuario) {
        entradaService.registrarEntrada(request, idUsuario);
        return ResponseEntity.ok("Entrada registrada correctamente");
    }

    @PostMapping("/masivas/{idUsuario}")
    public ResponseEntity<List<Entrada>> guardarEntradasMasivas(
            @RequestBody List<GuardarEntradaRequest> requestList,
            @PathVariable UUID idUsuario) {
        List<Entrada> entradasGuardadas = entradaService.guardarEntradasMasivas(requestList, idUsuario);
        return ResponseEntity.ok(entradasGuardadas);
    }

    @PutMapping("/editar/{idEntrada}")
    public ResponseEntity<String> actualizarEstado(@PathVariable UUID idEntrada) {
        entradaService.actualizarEstadoEntrada(idEntrada);
        return ResponseEntity.ok("Estado actualizado correctamente");
    }

    @PutMapping("/estado-masivo")
    public ResponseEntity<?> actualizarEstadoMasivo(@RequestBody ActualizarEstadoMasivoRequest request) {
        entradaService.actualizarEstadoMasivo(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listar/{idUsuario}")
    public ResponseEntity<List<EntradaRequest>> obtenerEntradasPorUsuario(@PathVariable UUID idUsuario) {
        List<EntradaRequest> entradas = entradaService.listarEntradasPorUsuario(idUsuario);
        return ResponseEntity.ok(entradas);
    }

    @PostMapping("/listar/filtro/{idUsuario}")
    public ResponseEntity<List<EntradaRequest>> buscarEntradasPorUsuarioConFiltros(
            @PathVariable UUID idUsuario,
            @RequestBody EntradaSearchRequest filtro) {

        List<EntradaRequest> entradas = entradaService.buscarEntradasPorUsuarioYFiltros(idUsuario, filtro);
        return ResponseEntity.ok(entradas);
    }

    @PostMapping("/listar/todas")
    public ResponseEntity<List<EntradasResponse>> buscarEntradas(@RequestBody EntradaSearchRequest filtro) {
        List<EntradasResponse> resultados = entradaService.buscarEntradasPorFiltros(filtro);
        return ResponseEntity.ok(resultados);
    }

    @PostMapping("/validar-qr")
    public ResponseEntity<String> validarQr(@RequestBody Map<String, String> body) {
        try {
            UUID codigoQr = UUID.fromString(body.get("codigoQr"));
            String mensaje = entradaService.validarEntradaPorQr(codigoQr);
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Código QR inválido");
        }
    }

    // Eliminar una entrada específica
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarEntradaPorId(@PathVariable UUID id) {
        entradaService.eliminarEntradaPorId(id);
        return ResponseEntity.noContent().build();
    }

    // Eliminar todas las entradas
    @DeleteMapping("/eliminar/todas")
    public ResponseEntity<Void> eliminarTodasLasEntradas() {
        entradaService.eliminarTodasLasEntradas();
        return ResponseEntity.noContent().build();
    }

}

