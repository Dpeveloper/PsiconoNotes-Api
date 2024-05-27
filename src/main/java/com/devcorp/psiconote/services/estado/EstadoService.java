package com.devcorp.psiconote.services.estado;

import com.devcorp.psiconote.dtos.EstadoDto;
import com.devcorp.psiconote.dtos.EstadoToSaveDto;

import java.util.List;
import java.util.Optional;

public interface EstadoService {
    EstadoDto guardarEstado(EstadoToSaveDto estado);
    EstadoDto actualizarEstado(Long id, EstadoToSaveDto estado);
    EstadoDto buscarEstadoId(Long id) throws Exception;
    EstadoDto buscarEstadoPorNombre(String nombreEstado) throws Exception;
    List<EstadoDto> buscarTodosEstados();
    void eliminarEstado(Long id);
}
