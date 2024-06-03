package com.devcorp.psiconote.dtos;

import com.devcorp.psiconote.entities.Informe;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record SesionDto(Long id,
                        LocalDate fecha,
                        LocalTime horaInicio,
                        LocalTime horaFinal,
                        String lugarSesion,
                        PacienteDto paciente,
                        PsicologoDto psicologo,
                        EstadoDto estado,
                        InformeDto informe) {}
