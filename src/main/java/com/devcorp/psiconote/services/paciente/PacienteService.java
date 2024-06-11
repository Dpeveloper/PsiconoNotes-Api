package com.devcorp.psiconote.services.paciente;

import com.devcorp.psiconote.dtos.EstadoDto;
import com.devcorp.psiconote.dtos.PacienteDto;
import com.devcorp.psiconote.dtos.PacienteToSaveDto;
import com.devcorp.psiconote.entities.Paciente;
import com.devcorp.psiconote.entities.Sesion;

import java.util.List;

public interface PacienteService {
    PacienteDto guardarPaciente(PacienteToSaveDto paciente);
    PacienteDto actualizarPaciente(PacienteDto pacienteDto);
    PacienteDto buscarPacientePorId(Long id);
    List<PacienteDto> buscarPacientePorNombre(String nombre);
    List<PacienteDto> buscarPacientes();
    List<PacienteDto> buscarPacientesActivos();

    PacienteDto actualizarEstado(Long id, String estado);
}
