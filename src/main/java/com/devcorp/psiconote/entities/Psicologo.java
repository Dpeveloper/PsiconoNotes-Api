package com.devcorp.psiconote.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity(name = "Piscologos")
public class Psicologo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private String apellido;
    private String estado;
    private int edad;
    private String telefono;

    @OneToOne
    @JoinColumn(name = "usuario_id",referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(mappedBy = "psicologo",cascade = CascadeType.PERSIST)
    private List<Paciente> pacientes;

    public void setPacientes(Paciente pacientes) {
        this.pacientes.add(pacientes);
    }
}
