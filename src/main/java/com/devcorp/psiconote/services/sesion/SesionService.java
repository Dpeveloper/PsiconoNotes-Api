package com.devcorp.psiconote.services.sesion;

import com.devcorp.psiconote.dtos.InformeToSaveDto;
import com.devcorp.psiconote.dtos.SesionDto;
import com.devcorp.psiconote.dtos.SesionToSaveDto;
import com.devcorp.psiconote.entities.Informe;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public interface SesionService {
    SesionDto guardarSesion(SesionToSaveDto sesionToSaveDto);
    SesionDto actualizarSesion(Long id, SesionToSaveDto sesionToSaveDto);
    SesionDto obtenerSesionPorId(Long id);
    List<SesionDto> obtenerSesionesPorEstado(String nombreEstado);
    List<SesionDto> obtenerSesionPorFecha(String fecha);
    List<SesionDto> obtenerSesionesPorPsicologo(Long idPsicologo);
    List<SesionDto> obtenerSesionesPorPaciente(Long idPaciente);
    List<SesionDto> obtenerSesiones();
    SesionDto actualizarInformeSesion(Long idSesion,Long idInforme);
    SesionDto actualizarEstadoSesion(String nombreEstado, Long idSesion);
    void eliminarSesion(Long id, String motivo);
    SesionDto reagendarSesion(Long idSesion, LocalDate fecha, String lugarSesion);
    SesionDto cancelarSesion(Long id, String notificacion);

    Object solicitarCancelarSesion(Long id, String notificacion);

    Object solicitarReagendarSesion(Long id, String notificacion);
}
