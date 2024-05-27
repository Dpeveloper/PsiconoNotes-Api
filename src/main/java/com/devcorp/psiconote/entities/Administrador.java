package com.devcorp.psiconote.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
@Entity(name = "Administradores")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private Date fechaNacimiento;

    @OneToOne
    @JoinColumn(name = "idUsuario",referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(mappedBy = "administrador")
    private List<Sede> sedes;
}


