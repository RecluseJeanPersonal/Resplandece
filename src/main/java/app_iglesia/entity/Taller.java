package app_iglesia.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "taller")
@Getter
@Setter
public class Taller {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "taller_nombre")
    private String nombre;

    @Column(name = "taller_descripcion")
    private String descripcion;

    @Column(name = "taller_habilitado")
    private Boolean habilitado;

    @Column(name = "taller_bloque")
    private String bloque;

    @Column(name = "taller_inicio")
    private String inicio;

    @Column(name = "taller_fin")
    private String fin;

    @Column(name = "taller_anio")
    private String anio;
}

