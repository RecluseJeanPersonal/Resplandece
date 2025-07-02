package app_iglesia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {

    //Atributos
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id_usuario")
    private UUID id;

    @Column(name = "tx_username")
    private String username;

    @Column(name = "tx_password")
    private String password;

    @Column(name = "tx_nombre")
    private String nombre;

    @Column(name = "tx_apellido")
    private String apellido;

    @Column(name = "tx_telefono")
    private String telefono;

    @Column(name = "dt_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "dt_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "is_habilitado")
    private Boolean habilitado;

    @ManyToMany
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "id_rol"))
    @JsonIgnore
    private Set<Rol> roles;

    //Constructores

    public Usuario() {
    }

    //-- Registrar Usuario
    public Usuario(String username, String password, String nombre, String apellido, String telefono,LocalDateTime fechaRegistro, Boolean habilitado, Set<Rol> roles) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.habilitado = habilitado;
        this.roles = roles;
    }

    //-- Consultar Usuario: Validación
    public Usuario(String username) {
        this.username = username;
    }

    //-- Consultar Usuario: Anexo -> UserDetailsImpl
    public Usuario(UUID id, String username, String password, Boolean habilitado) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.habilitado = habilitado;
    }

    //-- Consultar Usuario: Anexo -> Authentication
    public Usuario(UUID id, String username) {
        this.id = id;
        this.username = username;
    }
}
