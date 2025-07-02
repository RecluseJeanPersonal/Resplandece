package app_iglesia.payload.request;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class GuardarEntradaRequest {

    private String nombre;
    private String apellido;
    private Integer edad;
    private String telefono;
    private String estado;
    private String fechanac;
    private List<UUID> talleresIds;
}
