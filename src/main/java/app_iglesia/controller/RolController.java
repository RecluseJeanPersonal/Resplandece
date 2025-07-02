package app_iglesia.controller;

import app_iglesia.entity.Rol;
import app_iglesia.payload.response.RolResponse;
import app_iglesia.service.RolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<RolResponse>> listarRoles() {
        List<RolResponse> roles = rolService.listarRoles();
        return ResponseEntity.ok(roles);
    }

    @PostMapping("/crear")
    public ResponseEntity<Rol> crearRol(@RequestBody Rol rol) {
        Rol nuevoRol = rolService.crearRol(rol);
        return new ResponseEntity<>(nuevoRol, HttpStatus.CREATED);
    }
}