package com.devcorp.psiconote.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "Instituciones")
public class Institucion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "id_admin",referencedColumnName = "id")
    private Administrador administrador;

    @OneToMany(mappedBy = "institucion")
    private List<Grado> grados;
}
