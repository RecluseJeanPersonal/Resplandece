package app_iglesia.service.entradas;

import app_iglesia.payload.request.EntradaSearchRequest;
import app_iglesia.payload.response.EntradasResponse;

import java.util.List;

public interface EntradaRepositoryCustom {
    List<EntradasResponse> buscarPorFiltros(EntradaSearchRequest filtro);
}
