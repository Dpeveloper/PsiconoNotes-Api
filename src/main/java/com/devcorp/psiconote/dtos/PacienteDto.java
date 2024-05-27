package com.devcorp.psiconote.dtos;

public record PacienteDto(String nombre, String apellido, int edad, String genero, String email, String telefono, String acudiente, String telEmergencia, String telAcudiente, String estado) {
}
