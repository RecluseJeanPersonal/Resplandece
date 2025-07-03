package app_iglesia.service.usuario;

import app_iglesia.entity.Usuario;
import app_iglesia.payload.request.CrearUsuarioRequest;
import app_iglesia.payload.request.UsuarioEncargadoRequest;
import app_iglesia.payload.request.UsuarioSearchRequest;
import app_iglesia.payload.response.UsuarioListarResponse;

import java.util.List;
import java.util.UUID;

public interface UsuarioService {
    void crearUsuario(CrearUsuarioRequest request);
    Usuario obtenerPorId(UUID id);
    Usuario registrarUsuario(UsuarioEncargadoRequest dto);
    List<UsuarioListarResponse> buscarUsuarios(UsuarioSearchRequest request);

}