package com.devcorp.psiconote.dtos;

public record PsicologoToSaveDto(String nombre,
                                 String apellido,
                                 String estado,
                                 Integer edad,
                                 String telefono,
                                 String email,
                                 UsuarioToSaveDto usuario) {
}
