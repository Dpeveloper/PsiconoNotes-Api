package com.devcorp.psiconote.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario_id",referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(mappedBy = "psicologo", fetch = FetchType.EAGER)
    private List<Paciente> pacientes;
}
