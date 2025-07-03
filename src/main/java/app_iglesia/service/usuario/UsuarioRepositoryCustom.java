package app_iglesia.service.usuario;

import app_iglesia.payload.request.UsuarioSearchRequest;
import app_iglesia.payload.response.UsuarioListarResponse;

import java.util.List;

public interface UsuarioRepositoryCustom {
    List<UsuarioListarResponse> buscarUsuarios(UsuarioSearchRequest request);
}