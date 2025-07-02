package app_iglesia.payload.request;

import java.util.List;
import java.util.UUID;

public class ActualizarEstadoMasivoRequest {
    private List<UUID> idsEntradas;

    public List<UUID> getIdsEntradas() {
        return idsEntradas;
    }
    public void setIdsEntradas(List<UUID> idsEntradas) {
        this.idsEntradas = idsEntradas;
    }
}
