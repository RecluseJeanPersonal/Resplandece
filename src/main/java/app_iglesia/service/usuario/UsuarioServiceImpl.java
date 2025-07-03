package app_iglesia.service.usuario;

import app_iglesia.entity.Rol;
import app_iglesia.entity.Usuario;
import app_iglesia.payload.request.CrearUsuarioRequest;
import app_iglesia.payload.request.UsuarioEncargadoRequest;
import app_iglesia.payload.request.UsuarioSearchRequest;
import app_iglesia.payload.response.UsuarioListarResponse;
import app_iglesia.repository.rol.RolRepository;
import app_iglesia.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              RolRepository rolRepository,
                              PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void crearUsuario(CrearUsuarioRequest request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe.");
        }

        Rol rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado."));

        Usuario nuevoUsuario = new Usuario(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getNombre(),
                request.getApellido(),
                request.getTelefono(),
                LocalDateTime.now(),
                true,
                Collections.singleton(rol)
        );

        usuarioRepository.save(nuevoUsuario);
    }

    @Override
    public List<UsuarioListarResponse> buscarUsuarios(UsuarioSearchRequest request) {
        return usuarioRepository.buscarUsuarios(request);
    }

    @Override
    public Usuario obtenerPorId(UUID id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario registrarUsuario(UsuarioEncargadoRequest dto) {
        if (usuarioRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe.");
        }

        Rol rol = rolRepository.findByDescripcion(dto.getDescripcionRol())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rol no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setTelefono(dto.getTelefono());
        usuario.setFechaRegistro(LocalDateTime.now());
        usuario.setHabilitado(true);
        usuario.setRoles(Collections.singleton(rol));

        return usuarioRepository.save(usuario);
    }

}