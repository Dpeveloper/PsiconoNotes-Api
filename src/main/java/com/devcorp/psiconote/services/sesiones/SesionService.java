package com.devcorp.psiconote.services.sesiones;

import com.devcorp.psiconote.dtos.SesionDto;

import java.util.List;

public interface SesionService {
    SesionDto addSesion(SesionDto sesion);
    List<SesionDto> obtenerTodasLasSesiones();
}
