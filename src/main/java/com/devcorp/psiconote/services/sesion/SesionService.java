package com.devcorp.psiconote.services.sesion;

import com.devcorp.psiconote.dtos.SesionDto;
import com.devcorp.psiconote.dtos.SesionToSaveDto;

import java.util.List;
import java.util.stream.Stream;

public interface SesionService {
    SesionDto guardarSesion(SesionToSaveDto sesionToSaveDto);
    SesionDto actualizarSesion(Long id, SesionToSaveDto sesionToSaveDto);
    SesionDto obtenerSesionPorId(Long id);
    List<SesionDto> obtenerSesionesPorEstado(String nombreEstado);
    List<SesionDto> obtenerSesionPorFechaYHora(String fechaYHora);
    List<SesionDto> obtenerSesionesPorPsicologo(Long idPsicologo);
    List<SesionDto> obtenerSesionesPorPaciente(Long idPaciente);
    List<SesionDto> obtenerSesiones();
    void eliminarSesion(Long id);
}
