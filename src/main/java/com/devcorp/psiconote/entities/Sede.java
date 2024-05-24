package com.devcorp.psiconote.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "sedes")
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nombreSede;
    @ManyToOne
    @JoinColumn(name = "id_admin",referencedColumnName = "id")
    private Administrador administrador;

    @OneToMany(mappedBy = "sede")
    private List<Grado> grados;
}
