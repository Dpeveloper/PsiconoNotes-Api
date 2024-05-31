package com.devcorp.psiconote.services.paciente;

import com.devcorp.psiconote.dtos.EstadoDto;
import com.devcorp.psiconote.dtos.PacienteDto;
import com.devcorp.psiconote.dtos.mappers.PacienteMapper;
import com.devcorp.psiconote.entities.Paciente;
import com.devcorp.psiconote.repository.PacienteRepository;

import java.util.List;
import java.util.Optional;

public class PacienteServiceImp implements PacienteService {

    public PacienteRepository pacienteRepository;
    public PacienteMapper pacienteMapper;

    public PacienteServiceImp(PacienteMapper pacienteMapper, PacienteRepository pacienteRepository) {
        this.pacienteMapper = pacienteMapper;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public PacienteDto guardarPaciente(PacienteDto pacienteDto) {
       return pacienteMapper.toPacienteDto(pacienteRepository.save(pacienteMapper.toPaciente(pacienteDto)));
    }

    @Override
    public PacienteDto actualizarPaciente(PacienteDto pacienteDto) {
        return null;
    }

    @Override
    public PacienteDto buscarPacientePorId(Long id) {
        return pacienteMapper.toPacienteDto(pacienteRepository.findById(id).get());
    }

    @Override
    public List<PacienteDto> buscarPacientes() {
        return pacienteRepository.findAll().stream()
                .map(paciente -> pacienteMapper.toPacienteDto(paciente)).toList();
    }

    @Override
    public PacienteDto actualizarEstado(Long id, EstadoDto estadoDto) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            paciente.get().setEstado(estadoDto.nombreEstado());
            return pacienteMapper.toPacienteDto(pacienteRepository.save(paciente.get()));
        }

        return null;
    }
}
