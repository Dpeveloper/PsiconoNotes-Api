package com.devcorp.psiconote.services.psicologo;

import com.devcorp.psiconote.dtos.InformeDto;
import com.devcorp.psiconote.dtos.PsicologoDto;
import com.devcorp.psiconote.dtos.PacienteDto;

import java.util.List;


public interface PsicolgoService {

    public PsicologoDto actualizarPsicolgo(PsicologoDto psicologo, Long id);
    public PacienteDto agregarPaciente(PacienteDto pacientes);
    public PacienteDto actualizarPaciente(PacienteDto paciente, Long id);
    public PacienteDto actualizarEstadoPaciente(String estadoPaciente, Long id);
    public List<PacienteDto> buscarTodosLosPacientes();
    //vuelve a revisar, ¿como se genera un informe?
    public InformeDto generarInformePaciente(Long id);
    public InformeDto crearInforme(InformeDto informe);
    public InformeDto actualizarInforme(InformeDto informe);
    public List<InformeDto> buscarTodosLosInformes();
    public List<InformeDto> buscarInformesDelPaciente(Long id);
    //buscar sesiones proximas

}
