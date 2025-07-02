package app_iglesia.payload.response;

import java.util.UUID;

public class TallerResponse {
    private UUID id;
    private String nombre;
    private String descripcion;
    private String bloque;
    private String inicio;
    private String fin;
    private String anio;
    public TallerResponse(UUID id, String nombre, String descripcion, String bloque,
                          String inicio, String fin, String anio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.bloque = bloque;
        this.inicio = inicio;
        this.fin = fin;
        this.anio = anio;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getBloque() {
        return bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }
}
