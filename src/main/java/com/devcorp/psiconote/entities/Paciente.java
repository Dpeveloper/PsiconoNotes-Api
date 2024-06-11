package com.devcorp.psiconote.entities;

import com.devcorp.psiconote.dtos.EstadoToSaveDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private String estado;
    private String grado;
    @ManyToOne
    @JoinColumn(name = "psicologo",referencedColumnName = "id")
    private Psicologo psicologo;



    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario",referencedColumnName = "id")
    private Usuario usuario;

}
