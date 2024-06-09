package com.devcorp.psiconote.dtos;

import com.devcorp.psiconote.entities.Informe;

import java.time.LocalDateTime;

public record SesionDto(Long id,
                        String fechaYHora,
                        String lugarSesion,
                        String notificacion,
                        PacienteDto paciente,
                        PsicologoDto psicologo,
                        EstadoDto estado,
                        InformeDto informe) {}
