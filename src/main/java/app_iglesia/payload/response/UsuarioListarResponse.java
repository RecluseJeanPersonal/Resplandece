package app_iglesia.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioListarResponse {
    private UUID id;
    private String username;
    private String nombre;
    private String apellido;
    private String telefono;
    private Boolean habilitado;
    private Set<String> roles; // Solo los nombres de los roles
}
