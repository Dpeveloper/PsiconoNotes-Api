package com.devcorp.psiconote.dtos;

import java.util.Date;

public record AdministradorDto(String nombre, String apellido, String email, Date fechaNacimiento) {
}
