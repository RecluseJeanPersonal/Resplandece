package app_iglesia.controller;

import app_iglesia.service.entradas.EntradaService;
import app_iglesia.service.reportes.PdfQrService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pdf")
public class PdfQrController {

    private final PdfQrService pdfQrService;
    private final EntradaService entradaService;

    public PdfQrController(PdfQrService pdfQrService, EntradaService entradaService) {
        this.pdfQrService = pdfQrService;
        this.entradaService = entradaService;
    }

    @PostMapping(value = "/generar-masivo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> generarPdfMasivoPorIds(@RequestBody List<UUID> ids) {
        try {
            byte[] pdfBytes = pdfQrService.generarPdfConIds(ids);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "constancias_qr.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
