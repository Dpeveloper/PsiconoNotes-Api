package com.devcorp.psiconote.services.paciente;

import com.devcorp.psiconote.dtos.EstadoDto;
import com.devcorp.psiconote.dtos.PacienteDto;
import com.devcorp.psiconote.dtos.mappers.EstadoMapper;
import com.devcorp.psiconote.dtos.mappers.PacienteMapper;
import com.devcorp.psiconote.entities.Estado;
import com.devcorp.psiconote.entities.Paciente;
import com.devcorp.psiconote.entities.Psicologo;
import com.devcorp.psiconote.repository.PacienteRepository;
import com.devcorp.psiconote.repository.PsicologoRepository;
import com.devcorp.psiconote.services.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteServiceImp implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PsicologoRepository psicologoRepository;
    private final PacienteMapper pacienteMapper;
    private final EstadoMapper estadoMapper;

    @Autowired
    public PacienteServiceImp(PacienteMapper pacienteMapper, PacienteRepository pacienteRepository, PsicologoRepository psicologoRepository, EstadoMapper estadoMapper) {
        this.pacienteMapper = pacienteMapper;
        this.pacienteRepository = pacienteRepository;
        this.psicologoRepository = psicologoRepository;
        this.estadoMapper = estadoMapper;
    }

    @Override
    public PacienteDto guardarPaciente(Long psicologoId, PacienteDto pacienteDto) {
        Optional<Psicologo> psicologoOpt = psicologoRepository.findById(psicologoId);
        if (psicologoOpt.isPresent()) {
            Psicologo psicologo = psicologoOpt.get();
            Paciente paciente = pacienteMapper.toPaciente(pacienteDto);
            paciente.setPsicologo(psicologo);
            Paciente savedPaciente = pacienteRepository.save(paciente);
            return pacienteMapper.toPacienteDto(savedPaciente);
        }
        throw new ResourceNotFoundException("Psicólogo no encontrado");
    }

    @Override
    public PacienteDto actualizarPaciente(PacienteDto pacienteDto) {
        Paciente pacienteExistente = pacienteRepository.findById(pacienteDto.id())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));

        // Actualizar los campos de la entidad existente
        pacienteExistente.setNombre(pacienteDto.nombre());
        pacienteExistente.setApellido(pacienteDto.apellido());
        pacienteExistente.setEdad(pacienteDto.edad());
        pacienteExistente.setGenero(pacienteDto.genero());
        pacienteExistente.setEmail(pacienteDto.email());
        pacienteExistente.setTelefono(pacienteDto.telefono());
        pacienteExistente.setAcudiente(pacienteDto.acudiente());
        pacienteExistente.setTelEmergencia(pacienteDto.telEmergencia());
        pacienteExistente.setTelAcudiente(pacienteDto.telAcudiente());

        // Guardar los cambios
        Paciente pacienteActualizado = pacienteRepository.save(pacienteExistente);
        return pacienteMapper.toPacienteDto(pacienteActualizado);
    }

    @Override
    public PacienteDto buscarPacientePorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
        return pacienteMapper.toPacienteDto(paciente);
    }

    @Override
    public List<PacienteDto> buscarPacientes() {
        return pacienteRepository.findAll().stream()
                .map(pacienteMapper::toPacienteDto)
                .collect(Collectors.toList());
    }

    @Override
    public PacienteDto actualizarEstado(Long id, EstadoDto estadoDto) {
        /*Paciente pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));

        Estado nuevoEstado = estadoMapper.toEntity(estadoDto);
        pacienteExistente.g(nuevoEstado);

        Paciente pacienteActualizado = pacienteRepository.save(pacienteExistente);
        return pacienteMapper.toPacienteDto(pacienteActualizado);

         */
        return null;
    }
}
