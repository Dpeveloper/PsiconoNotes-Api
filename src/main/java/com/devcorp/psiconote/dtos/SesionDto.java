package com.devcorp.psiconote.dtos;

import java.time.LocalDateTime;

public record SesionDto(Long id, LocalDateTime fechaYHora, String lugarSesion) {
}
