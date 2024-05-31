package com.devcorp.psiconote.services.sesiones;

import com.devcorp.psiconote.dtos.PacienteDto;
import com.devcorp.psiconote.dtos.SesionDto;

import java.util.List;

public interface SesionService {
    SesionDto agregarSesion(SesionDto sesion, Long pacienteId);
    List<SesionDto> obtenerTodasLasSesiones();
    List<SesionDto> obtenerProximasSesionesPorPsicologo(Long id);
    List<SesionDto> obtenerProximasSesionesPorUsuario(Long id);
    SesionDto obtenerSesionPorId(Long id);
    List<SesionDto> cancelarSesion(Long id);
}
