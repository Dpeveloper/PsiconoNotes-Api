package com.devcorp.psiconote.services.informe;

import com.devcorp.psiconote.dtos.InformeDto;

import java.util.List;

public interface InformeService {

    InformeDto registrarInforme(Long pacienteId, InformeDto informeDto);
    InformeDto actualizarInforme(Long pacienteId, InformeDto informeDto);
    InformeDto obtenerInforme(Long pacienteId, Long informe);
    List<InformeDto> buscarTodosLosInformes(Long pacienteId);


}
