package com.devcorp.psiconote.dtos;

import java.time.LocalDateTime;

public record SesionDto(Long id,
                        String fechaYHora,
                        String lugarSesion,
                        PacienteDto paciente,
                        PsicologoDto psicologo,
                        EstadoDto estado) {}
