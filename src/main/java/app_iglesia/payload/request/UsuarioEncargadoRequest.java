package app_iglesia.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioEncargadoRequest {
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String telefono;
    private String descripcionRol; // Este será el tx_descripcion del rol
}
