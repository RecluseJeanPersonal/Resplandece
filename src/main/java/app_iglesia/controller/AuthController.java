package app_iglesia.controller;

import app_iglesia.constant.ControllerConstant;
import app_iglesia.payload.request.LoginRequest;
import app_iglesia.payload.request.UsuarioEncargadoRequest;
import app_iglesia.payload.response.JwtResponse;
import app_iglesia.security.jwt.JwtProvider;
import app_iglesia.security.payload.JwtTimesResponse;
import app_iglesia.security.service.UserDetailsImpl;
import app_iglesia.service.usuario.UsuarioService;
import app_iglesia.util.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    ControllerConstant controllerConstant;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager, JwtProvider jwtProvider, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Map<String, String>> registrarUsuario(@RequestBody UsuarioEncargadoRequest dto) {
        usuarioService.registrarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("mensaje", "Usuario creado correctamente"));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generar el JWT token
            String token = jwtProvider.generateJWT(authentication);

            // Obtener el nombre de usuario
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String username = userDetails.getUsername();
            UUID idusuario = userDetails.getId();

            // Obtener los roles desde las autoridades
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            // Obtener los tiempos de emisión y expiración del token
            JwtTimesResponse fechas = jwtProvider.getTimesFromJWT(token);

            // Crear la respuesta con el token, nombre de usuario, roles y tiempos
            return ResponseEntity.ok(new JwtResponse(
                    token,
                    username,
                    roles,
                    fechas.getEmision(),
                    fechas.getExpiracion(),
                    idusuario
            ));
        }  catch (Exception e) {
            System.out.println("******************");
            System.out.println(e.getMessage());
            // Puedes revisar el tipo de excepción para ser más específico si deseas
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("bad credentials")) {
                throw new Exceptions(controllerConstant.CREDENCIALES_INVALIDAS);
            } else {
                throw new Exceptions(controllerConstant.ERROR_SERVER);
            }
        }
    }
}
