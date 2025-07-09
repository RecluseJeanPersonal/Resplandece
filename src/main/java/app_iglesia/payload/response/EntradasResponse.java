package app_iglesia.payload.response;

import java.time.LocalDateTime;
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
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaRegistroActualizada;
    private String tipo;
    private List<TallerResponse> talleres;  // lista de talleres simplificada

    public EntradasResponse(UUID id, String nombre, String apellido, String fechanac, Integer edad,
                      String telefono, String estado, String tipo, LocalDateTime fechaRegistro, LocalDateTime fechaRegistroActualizada, List<TallerResponse> talleres) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechanac = fechanac;
        this.edad = edad;
        this.telefono = telefono;
        this.estado = estado;
        this.tipo = tipo;
        this.fechaRegistro = fechaRegistro;
        this.fechaRegistroActualizada = fechaRegistroActualizada;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaRegistroActualizada() {
        return fechaRegistroActualizada;
    }

    public void setFechaRegistroActualizada(LocalDateTime fechaRegistroActualizada) {
        this.fechaRegistroActualizada = fechaRegistroActualizada;
    }

    public List<TallerResponse> getTalleres() {
        return talleres;
    }

    public void setTalleres(List<TallerResponse> talleres) {
        this.talleres = talleres;
    }
}
