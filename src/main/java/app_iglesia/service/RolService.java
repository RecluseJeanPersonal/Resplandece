package app_iglesia.service;

import app_iglesia.entity.Rol;
import app_iglesia.payload.response.RolResponse;
import java.util.List;

public interface RolService {
    List<RolResponse> listarRoles();
    Rol crearRol(Rol rol);
}