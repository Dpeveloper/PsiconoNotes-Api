package com.devcorp.psiconote.services.informe;

import com.devcorp.psiconote.dtos.InformeDto;
<<<<<<< HEAD
import com.devcorp.psiconote.dtos.InformeToSaveDto;
=======
>>>>>>> c2b5cd6c6495d6199b7e5e5780e3602de286ce03

import java.util.List;

public interface InformeService {
<<<<<<< HEAD
    InformeDto guardadInforme(InformeToSaveDto informeToSaveDto);
    InformeDto actualizarInforme(Long id, InformeToSaveDto informeToSaveDto);
    InformeDto obtenerInformePorId(Long id);
    List<InformeDto> obtenerTodosInformes();
    List<InformeDto> obtenerInformesPorPaciente(Long idPaciente);
    void eliminarInforme(Long id);
=======

    InformeDto registrarInforme(Long pacienteId, InformeDto informeDto);
    InformeDto actualizarInforme(Long pacienteId, InformeDto informeDto);
    InformeDto obtenerInforme(Long pacienteId, Long informe);
    List<InformeDto> buscarTodosLosInformes(Long pacienteId);


>>>>>>> c2b5cd6c6495d6199b7e5e5780e3602de286ce03
}
