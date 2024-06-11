package com.devcorp.psiconote.services.psicologo;

import com.devcorp.psiconote.dtos.*;
import com.devcorp.psiconote.entities.Sesion;

import java.util.List;


public interface PsicologoService {
    public PsicologoDto crearPsicologo(PsicologoToSaveDto psicologo);
    public PsicologoDto obtenerPsicologoPorId(Long id);
    public PsicologoDto actualizarPsicologo(PsicologoDto psicologo);
    public List<PsicologoDto> buscarTodosLosPsicologos();
    public List<PacienteDto> buscarTodosLosPacientes(Long psicologoId);
    public PsicologoDto actualizarSesiones(Long idPsicologo, Sesion sesion);
    public List<PsicologoDto> buscarPsicologoPorNombre(String nombrePsicologo);
    public InformeDto generarInformePaciente(Long pacienteId, InformeToSaveDto informeToSaveDto);

}
