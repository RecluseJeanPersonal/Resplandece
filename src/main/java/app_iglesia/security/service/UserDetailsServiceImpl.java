package app_iglesia.security.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import app_iglesia.entity.Rol;
import app_iglesia.entity.Usuario;
import app_iglesia.repository.rol.IRolDAO;
import app_iglesia.repository.usuario.IUsuarioDAO;

import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);

    final IUsuarioDAO dataUsuario;
    final IRolDAO dataRol;

    public UserDetailsServiceImpl(IUsuarioDAO dataUsuario, IRolDAO dataRol) {
        this.dataUsuario = dataUsuario;
        this.dataRol = dataRol;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("Inicio Método loadUserByUsername");

        Usuario usuario = dataUsuario.findUsuarioToUserDetailsImpl(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        logger.info("Se encontró el usuario: {}", usuario.getUsername());

        Usuario currentUsuario = new Usuario(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getHabilitado()
        );

        Set<Rol> roles = dataRol.findRolesByUsuario(usuario);

        return UserDetailsImpl.build(currentUsuario, roles);
    }
}
