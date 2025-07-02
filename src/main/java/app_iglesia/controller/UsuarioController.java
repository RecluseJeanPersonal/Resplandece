package app_iglesia.controller;

import app_iglesia.entity.Usuario;
import app_iglesia.payload.request.CrearUsuarioRequest;
import app_iglesia.payload.request.UsuarioEncargadoRequest;
import app_iglesia.payload.response.UsuarioListarResponse;
import app_iglesia.service.UsuarioService;
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

    @GetMapping("getAll")
    public ResponseEntity<List<UsuarioListarResponse>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
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
