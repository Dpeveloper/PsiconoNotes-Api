package com.devcorp.psiconote.dtos;

public record SesionToSaveDto(String fechaYHora,
                              String lugarSesion,
                              Long idPsicologo,
                              Long idPaciente,
                              EstadoToSaveDto estado) {}
