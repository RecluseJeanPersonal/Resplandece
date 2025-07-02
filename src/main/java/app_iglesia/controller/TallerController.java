package app_iglesia.controller;

import app_iglesia.entity.Taller;
import app_iglesia.service.TallerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/talleres")
@RequiredArgsConstructor
public class TallerController {

    private final TallerService tallerService;

    @PostMapping("/crear")
    public ResponseEntity<Taller> crearTaller(@RequestBody Taller taller) {
        return ResponseEntity.ok(tallerService.crearTaller(taller));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Taller> editarTaller(@PathVariable UUID id, @RequestBody Taller taller) {
        return ResponseEntity.ok(tallerService.editarTaller(id, taller));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Taller>> listarTalleres() {
        List<Taller> talleres = tallerService.listarTalleres();
        return new ResponseEntity<>(talleres, HttpStatus.OK);
    }
}