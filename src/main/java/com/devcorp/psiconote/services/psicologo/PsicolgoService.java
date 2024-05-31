package com.devcorp.psiconote.services.psicologo;

import com.devcorp.psiconote.dtos.EstadoToSaveDto;
import com.devcorp.psiconote.dtos.InformeDto;
import com.devcorp.psiconote.dtos.PsicologoDto;
import com.devcorp.psiconote.dtos.PacienteDto;
import com.devcorp.psiconote.entities.Informe;

import java.util.List;


public interface PsicolgoService {

    public PsicologoDto actualizarPsicolgo(PsicologoDto psicologo);
    public PacienteDto agregarPaciente(PacienteDto pacientes,Long psicologoId);
    public PacienteDto actualizarPaciente(PacienteDto paciente,Long psicologoId);
    public PacienteDto actualizarEstadoPaciente(EstadoToSaveDto estadoPaciente, Long id);
    public List<PacienteDto> buscarTodosLosPacientes(Long psicologoId);
    //vuelve a revisar, ¿como se genera un informe?
    public InformeDto generarInformePaciente(Long pacienteId, Long piscologoId, InformeDto informe);
    //public InformeDto registrarInforme(InformeDto informe);
    //public InformeDto actualizarInforme(InformeDto informe);
    //public List<InformeDto> buscarTodosLosInformes();
    //public List<InformeDto> buscarInformesDelPaciente(Long id);
    //buscar sesiones proximas
}
