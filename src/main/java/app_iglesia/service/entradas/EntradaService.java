package app_iglesia.service.entradas;

import app_iglesia.entity.Entrada;
import app_iglesia.payload.request.ActualizarEstadoMasivoRequest;
import app_iglesia.payload.request.EntradaRequest;
import app_iglesia.payload.request.EntradaSearchRequest;
import app_iglesia.payload.request.GuardarEntradaRequest;
import app_iglesia.payload.response.EntradasResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EntradaService {
    Entrada registrarEntrada(GuardarEntradaRequest request, UUID idUsuario);
    List<Entrada> guardarEntradasMasivas(List<GuardarEntradaRequest> requestList, UUID idUsuario);

    void actualizarEstadoEntrada(UUID idEntrada);
    void actualizarEstadoMasivo(ActualizarEstadoMasivoRequest request);

    List<EntradaRequest> listarEntradasPorUsuario(UUID idUsuario);
    Optional<Entrada> buscarPorId(UUID idEntrada);
    String validarEntradaPorQr(UUID codigoQr);
    List<EntradasResponse> buscarEntradasPorFiltros(EntradaSearchRequest filtro);
    void eliminarEntradaPorId(UUID idEntrada);
    void eliminarTodasLasEntradas();
    List<EntradaRequest> buscarEntradasPorUsuarioYFiltros(UUID idUsuario, EntradaSearchRequest filtro);

}