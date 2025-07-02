package app_iglesia.payload.request;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class EntradaRequest {
    private UUID id;
    private String nombre;
    private String apellido;
    private String fechanac;
    private Integer edad;
    private String telefono;
    private String estado;
    private UUID codigoQR;
    private LocalDateTime fechaRegistro;
    private List<TallerRequest> talleres;

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

    public UUID getCodigoQR() {
        return codigoQR;
    }

    public void setCodigoQR(UUID codigoQR) {
        this.codigoQR = codigoQR;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<TallerRequest> getTalleres() {
        return talleres;
    }

    public void setTalleres(List<TallerRequest> talleres) {
        this.talleres = talleres;
    }
}
