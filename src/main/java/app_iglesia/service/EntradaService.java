package app_iglesia.service;

import app_iglesia.entity.Entrada;
import app_iglesia.payload.request.ActualizarEstadoMasivoRequest;
import app_iglesia.payload.request.EntradaRequest;
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
    List<EntradasResponse> listarTodasEntradas();
    Optional<Entrada> buscarPorId(UUID idEntrada);
    String validarEntradaPorQr(UUID codigoQr);
}