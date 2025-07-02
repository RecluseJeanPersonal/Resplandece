package app_iglesia.payload.response;

import java.util.List;
import java.util.UUID;

public class EntradasResponse {
    private UUID id;
    private String nombre;
    private String apellido;
    private String fechanac;
    private Integer edad;
    private String telefono;
    private String estado;
    private List<TallerResponse> talleres;  // lista de talleres simplificada

    public EntradasResponse(UUID id, String nombre, String apellido, String fechanac, Integer edad,
                      String telefono, String estado, List<TallerResponse> talleres) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechanac = fechanac;
        this.edad = edad;
        this.telefono = telefono;
        this.estado = estado;
        this.talleres = talleres;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<TallerResponse> getTalleres() {
        return talleres;
    }

    public void setTalleres(List<TallerResponse> talleres) {
        this.talleres = talleres;
    }
}
