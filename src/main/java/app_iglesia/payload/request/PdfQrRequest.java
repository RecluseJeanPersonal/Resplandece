package app_iglesia.payload.request;

import java.util.UUID;

public class PdfQrRequest {
    private UUID codigoQr;
    private String nombreEntrada;

    public PdfQrRequest() {}

    public PdfQrRequest(UUID codigoQr, String nombreEntrada) {
        this.codigoQr = codigoQr;
        this.nombreEntrada = nombreEntrada;
    }

    public UUID getCodigoQr() {
        return codigoQr;
    }

    public void setCodigoQr(UUID codigoQr) {
        this.codigoQr = codigoQr;
    }

    public String getNombreEntrada() {
        return nombreEntrada;
    }

    public void setNombreEntrada(String nombreEntrada) {
        this.nombreEntrada = nombreEntrada;
    }
}
