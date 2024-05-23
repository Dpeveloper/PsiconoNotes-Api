package com.devcorp.psiconote.entities;

import jakarta.persistence.*;

public class Paciente {
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String email;
    private String telefono;
    private String acudiente;
    private String telEmergencia;
    private String telAcudiente;
    private String estado;
}
