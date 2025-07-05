package app_iglesia.service.entradas;

import app_iglesia.entity.Entrada;
import app_iglesia.payload.request.EntradaSearchRequest;
import app_iglesia.payload.response.EntradasResponse;

import java.util.List;
import java.util.UUID;

public interface EntradaRepositoryCustom {
    List<EntradasResponse> buscarPorFiltros(EntradaSearchRequest filtro);
    List<Entrada> buscarPorUsuarioYFiltros(UUID idUsuario, EntradaSearchRequest filtro);

}
