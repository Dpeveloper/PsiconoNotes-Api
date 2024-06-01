package com.devcorp.psiconote.entities;

import com.devcorp.psiconote.dtos.EstadoToSaveDto;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity(name = "Pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String email;
    private String telefono;
    private String acudiente;
    private String telEmergencia;
    private String telAcudiente;

    @ManyToOne
    @JoinColumn(name = "psicologo",referencedColumnName = "id")
    private Psicologo psicologo;

    @ManyToOne
    @JoinColumn(name = "grado", referencedColumnName = "id")
    private Grado grado;

    @OneToOne
    @JoinColumn(name = "usuario",referencedColumnName = "id")
    private Usuario usuario;

    @OneToOne(mappedBy = "paciente")
    private Informe informe;
}
