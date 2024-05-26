package com.devcorp.psiconote.entities;

import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "sesiones_canceladas")
public class SesionCancelada extends Sesion{
    private String motivoCancelacion;
}
