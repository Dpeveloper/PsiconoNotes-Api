package com.devcorp.psiconote.dtos;

import java.time.LocalDateTime;

<<<<<<< HEAD
public record SesionDto(Long id,
                        String fechaYHora,
                        String lugarSesion,
                        PacienteDto paciente,
                        PsicologoDto psicologo,
                        EstadoDto estado) {}
=======
public record SesionDto(Long id, LocalDateTime fechaYHora, String lugarSesion) {
}
>>>>>>> c2b5cd6c6495d6199b7e5e5780e3602de286ce03
