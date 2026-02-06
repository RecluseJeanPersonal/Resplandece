package app_iglesia.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CambiarRolRequest {

    private UUID idUsuario;
    private UUID idRol;
}
