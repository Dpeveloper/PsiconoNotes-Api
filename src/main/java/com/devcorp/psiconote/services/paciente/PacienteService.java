package com.devcorp.psiconote.services.paciente;

import com.devcorp.psiconote.dtos.EstadoDto;
import com.devcorp.psiconote.dtos.PacienteDto;
import com.devcorp.psiconote.dtos.PacienteToSaveDto;

import java.util.List;

public interface PacienteService {
    PacienteDto guardarPaciente(PacienteToSaveDto paciente);
    PacienteDto actualizarPaciente(PacienteDto pacienteDto);
    PacienteDto buscarPacientePorId(Long id);
    List<PacienteDto> buscarPacientes();
    PacienteDto actualizarEstado(Long id, EstadoDto estadoDto);

}
