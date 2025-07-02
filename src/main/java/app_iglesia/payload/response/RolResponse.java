package app_iglesia.payload.response;

import java.util.UUID;

public class RolResponse {
    private UUID id;
    private String descripcion;
    private String value;

    public RolResponse(UUID id, String descripcion, String value) {
        this.id = id;
        this.descripcion = descripcion;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}