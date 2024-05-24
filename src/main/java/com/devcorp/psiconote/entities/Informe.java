package com.devcorp.psiconote.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "informes")
public class Informe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resumen;
    private String objetivos;
    private String trabajoRealizado;
    private String observaciones;
    private String respuestasEstudiante;
    private String conclusiones;
    private String planAccion;
    private String notasAdicionales;

    //psicologo del informe
    @OneToOne(targetEntity = Psicologo.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "idPsicologo",referencedColumnName = "id")
    private Psicologo psicologo;

    //paciente del informe
    @OneToOne(targetEntity = Paciente.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "idPaciente",referencedColumnName = "id")
    private Paciente paciente;
}
