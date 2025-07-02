package app_iglesia.payload.response;

import java.util.Set;
import java.util.UUID;

public class UsuarioResponse {
    private UUID id;
    private String username;
    private String password;
    private Boolean habilitado;

    private Set<UUID> idsRoles;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Set<UUID> getIdsRoles() {
        return idsRoles;
    }

    public void setIdsRoles(Set<UUID> idsRoles) {
        this.idsRoles = idsRoles;
    }
}
