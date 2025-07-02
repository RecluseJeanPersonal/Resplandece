package app_iglesia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "rol")
@Getter
@Setter
public class Rol {

    //Atributos
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id_rol")
    private UUID id;

    @Column(name = "tx_descripcion", unique = true)
    private String descripcion;

    @Column(name = "tx_value", unique = true)
    private String value;

    @Column(name = "is_habilitado")
    private Boolean habilitado;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios;

    //Constructores

    public Rol() {
    }

    //-- Consultar Rol: Existencia
    public Rol(UUID id, Boolean habilitado) {
        this.id = id;
        this.habilitado = habilitado;
    }

    //-- Consultar Roles: Anexo -> UserDetailsImpl
    public Rol(UUID id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
}
