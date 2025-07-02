package app_iglesia.service;

import app_iglesia.entity.Rol;
import app_iglesia.payload.response.RolResponse;
import app_iglesia.repository.rol.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    @Autowired
    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<RolResponse> listarRoles() {
        List<Rol> roles = rolRepository.findByHabilitadoTrue(); // o findAll()
        return roles.stream()
                .map(rol -> new RolResponse(rol.getId(), rol.getDescripcion(), rol.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public Rol crearRol(Rol rol) {
        return rolRepository.save(rol);
    }
}