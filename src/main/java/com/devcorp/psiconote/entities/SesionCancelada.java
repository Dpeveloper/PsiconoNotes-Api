package com.devcorp.psiconote.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sesiones_canceladas")
public class SesionCancelada{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaYHora;
    private String lugarSesion;

    //paciente dentro de la sesión
    @OneToOne(targetEntity = Paciente.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "idPaciente",referencedColumnName = "id")
    private Paciente paciente;

    //psicologo dentro de la sesión
    @OneToOne(targetEntity = Psicologo.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "idPsicologo",referencedColumnName = "id")
    private Psicologo psicologo;

    //estado de la sesión
    @ManyToOne(targetEntity = Estado.class,fetch = FetchType.EAGER)
    @JoinColumn(name="idEstado",referencedColumnName = "id")
    private Estado estado;

    private String motivoCancelacion;
}
