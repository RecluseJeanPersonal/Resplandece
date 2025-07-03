package app_iglesia.controller;

import app_iglesia.entity.Usuario;
import app_iglesia.payload.request.CrearUsuarioRequest;
import app_iglesia.payload.request.UsuarioSearchRequest;
import app_iglesia.payload.response.UsuarioListarResponse;
import app_iglesia.service.usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("crear")
    public ResponseEntity<String> crearUsuario(@RequestBody CrearUsuarioRequest request) {
        usuarioService.crearUsuario(request);
        return ResponseEntity.ok("Usuario creado correctamente");
    }

    @PostMapping("getAll")
    public ResponseEntity<List<UsuarioListarResponse>> buscarUsuarios(@RequestBody UsuarioSearchRequest request) {
        return ResponseEntity.ok(usuarioService.buscarUsuarios(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable UUID id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

}
