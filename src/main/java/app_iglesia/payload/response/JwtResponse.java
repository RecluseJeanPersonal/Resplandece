package app_iglesia.payload.response;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class JwtResponse {
    private String token;
    private String username;
    private String nombre;
    private List<String> roles;  // Lista de roles
    private String emissionTime;
    private String expirationTime;
    private UUID idUsuario;

    public JwtResponse(String token, String username, String nombre, List<String> roles, String emissionTime, String expirationTime, UUID idUsuario) {
        this.token = token;
        this.username = username;
        this.nombre = nombre;
        this.roles = roles;
        this.emissionTime = emissionTime;
        this.expirationTime = expirationTime;
        this.idUsuario = idUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getEmissionTime() {
        return emissionTime;
    }

    public void setEmissionTime(String emissionTime) {
        this.emissionTime = emissionTime;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }
}
