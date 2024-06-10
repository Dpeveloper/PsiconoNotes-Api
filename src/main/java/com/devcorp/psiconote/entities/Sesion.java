package com.devcorp.psiconote.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sesiones")
public class Sesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private LocalDate fecha;
    @Temporal(TemporalType.TIME)
    private LocalTime horaInicio;
    @Temporal(TemporalType.TIME)
    private LocalTime horaFinal;
    private String lugarSesion;
    private String notificacion;

    //paciente dentro de la sesión
    @ManyToOne
    @JoinColumn(name = "idPaciente",referencedColumnName = "id")
    private Paciente paciente;

    //psicologo dentro de la sesión
    @ManyToOne
    @JoinColumn(name = "idPsicologo",referencedColumnName = "id")
    private Psicologo psicologo;

    //estado de la sesión
    @ManyToOne(targetEntity = Estado.class,fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name="idEstado",referencedColumnName = "id")
    private Estado estado;

    //informe dentro de la sesión
    @OneToOne(targetEntity = Informe.class,fetch = FetchType.EAGER)
    @JoinColumn(name="idInforme",referencedColumnName = "id")
    private Informe informe;
}
