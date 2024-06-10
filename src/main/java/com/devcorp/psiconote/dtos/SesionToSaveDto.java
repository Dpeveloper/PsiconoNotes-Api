package com.devcorp.psiconote.dtos;

public record SesionToSaveDto(String fecha,
                              String horaInicio,
                              String horaFinal,
                              String lugarSesion,
                              Long idPsicologo,
                              Long idPaciente,
                              EstadoToSaveDto estado) {}
