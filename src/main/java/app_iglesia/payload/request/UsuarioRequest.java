package app_iglesia.payload.request;

import java.util.Set;
import java.util.UUID;

public class UsuarioRequest {
    private UUID idCongregante;
    private String username;
    private String password;
    private Set<UUID> rolesIds; // Si también deseas asignar roles al crear

    public UUID getIdCongregante() {
        return idCongregante;
    }

    public void setIdCongregante(UUID idCongregante) {
        this.idCongregante = idCongregante;
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

    public Set<UUID> getRolesIds() {
        return rolesIds;
    }

    public void setRolesIds(Set<UUID> rolesIds) {
        this.rolesIds = rolesIds;
    }
}
