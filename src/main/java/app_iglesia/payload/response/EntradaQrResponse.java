package app_iglesia.payload.response;

import java.util.UUID;

public class EntradaQrResponse {
    private UUID id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String estado;
    private String tipo;
    private String tipoentrada;
    private UUID codigoQR;

    public EntradaQrResponse() {
    }

    public EntradaQrResponse(UUID id, String nombre, String apellido, String telefono, String estado,
                             String tipo, String tipoentrada, UUID codigoQR) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.estado = estado;
        this.tipo = tipo;
        this.tipoentrada = tipoentrada;
        this.codigoQR = codigoQR;
    }

    // Getters y Setters

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

    public String getTipoentrada() {
        return tipoentrada;
    }

    public void setTipoentrada(String tipoentrada) {
        this.tipoentrada = tipoentrada;
    }

    public UUID getCodigoQR() {
        return codigoQR;
    }

    public void setCodigoQR(UUID codigoQR) {
        this.codigoQR = codigoQR;
    }

}