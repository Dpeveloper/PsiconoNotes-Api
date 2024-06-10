package com.devcorp.psiconote.dtos;

import com.devcorp.psiconote.dtos.mappers.UsuarioMapper;

public record PsicologoToSaveDto(String nombre,
                                 String apellido,
                                 String estado,
                                 Integer edad,
                                 String telefono,
                                 UsuarioToSaveDto usuario) {
}
