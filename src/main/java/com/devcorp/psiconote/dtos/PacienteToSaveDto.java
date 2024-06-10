package com.devcorp.psiconote.dtos;

public record PacienteToSaveDto(String nombre,
                                String apellido,
                                Integer edad,
                                String genero,
                                String email,
                                String telefono,
                                String acudiente,
                                String telEmergencia,
                                String telAcudiente,
                                String estado,
                                UsuarioToSaveDto usuario) {}
