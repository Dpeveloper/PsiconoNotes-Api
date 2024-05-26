package com.devcorp.psiconote.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "Grados")
public class Grado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "sede",referencedColumnName = "id")
    private Sede sede;

    @OneToMany(mappedBy = "grado")
    private List<Paciente> pacientes;
}
