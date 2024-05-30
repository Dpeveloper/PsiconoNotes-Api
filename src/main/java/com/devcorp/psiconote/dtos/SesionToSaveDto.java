package com.devcorp.psiconote.dtos;

public record SesionToSaveDto(String fechaYHora,
                              String lugarSesion,
                              EstadoDto estado) {}
