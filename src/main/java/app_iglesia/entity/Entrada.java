package app_iglesia.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "entrada")
@Getter
@Setter
public class Entrada {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "tx_nombre")
    private String nombre;

    @Column(name = "tx_apellido")
    private String apellido;

    @Column(name = "tx_fechanac")
    private String fechanac;

    @Column(name = "nu_edad")
    private Integer edad;

    @Column(name = "tx_telefono")
    private String telefono;

    @Column(name = "tx_estado")
    private String estado;

    @ManyToMany
    @JoinTable(
            name = "entrada_taller",
            joinColumns = @JoinColumn(name = "id_entrada"),
            inverseJoinColumns = @JoinColumn(name = "id_taller")
    )
    private List<Taller> talleres;

    @GeneratedValue
    @UuidGenerator
    @Column(name = "tx_codigo_qr", unique = true)
    private UUID codigoQR;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "dt_registro")
    private LocalDateTime fechaRegistro;
}