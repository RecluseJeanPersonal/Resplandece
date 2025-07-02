package app_iglesia.service;

import app_iglesia.entity.Usuario;
import app_iglesia.payload.request.CrearUsuarioRequest;
import app_iglesia.payload.request.UsuarioEncargadoRequest;
import app_iglesia.payload.response.UsuarioListarResponse;

import java.util.List;
import java.util.UUID;

public interface UsuarioService {
    void crearUsuario(CrearUsuarioRequest request);
    List<UsuarioListarResponse> listarUsuarios();
    Usuario obtenerPorId(UUID id);
    Usuario registrarUsuario(UsuarioEncargadoRequest dto);

}