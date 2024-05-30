package com.devcorp.psiconote.services.informe;

import com.devcorp.psiconote.dtos.InformeDto;
import com.devcorp.psiconote.dtos.InformeToSaveDto;

import java.util.List;

public interface InformeService {
    InformeDto guardadInforme(InformeToSaveDto informeToSaveDto);
    InformeDto actualizarInforme(Long id, InformeToSaveDto informeToSaveDto);
    InformeDto obtenerInformePorId(Long id);
    List<InformeDto> obtenerTodosInformes();
    List<InformeDto> obtenerInformesPorPaciente(Long idPaciente);
    void eliminarInforme(Long id);
}
