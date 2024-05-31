package com.devcorp.psiconote.services.psicologo;

import com.devcorp.psiconote.dtos.InformeDto;
import com.devcorp.psiconote.dtos.PsicologoDto;
import com.devcorp.psiconote.dtos.PacienteDto;

import java.util.List;


public interface PsicologoService {

    public PsicologoDto actualizarPsicologo(PsicologoDto psicologo);
    public List<PacienteDto> buscarTodosLosPacientes(Long psicologoId);
    //vuelve a revisar, ¿como se genera un informe?
    public InformeDto generarInformePaciente(Long pacienteId, InformeDto informe);

}
