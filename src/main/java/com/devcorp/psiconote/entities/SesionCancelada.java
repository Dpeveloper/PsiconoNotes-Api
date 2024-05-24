package com.devcorp.psiconote.entities;

import jakarta.persistence.Table;

@Table(name = "sesiones_canceladas")
public class SesionCancelada extends Sesion{
    private String motivoCancelacion;
}
