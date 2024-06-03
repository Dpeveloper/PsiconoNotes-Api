package com.devcorp.psiconote.services.paciente;

import com.devcorp.psiconote.dtos.EstadoDto;
import com.devcorp.psiconote.dtos.PacienteDto;
import com.devcorp.psiconote.dtos.PacienteToSaveDto;
import com.devcorp.psiconote.dtos.mappers.EstadoMapper;
import com.devcorp.psiconote.dtos.mappers.PacienteMapper;
import com.devcorp.psiconote.entities.Estado;
import com.devcorp.psiconote.entities.Paciente;
import com.devcorp.psiconote.entities.Psicologo;
import com.devcorp.psiconote.entities.Sesion;
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

    @Autowired
    public PacienteServiceImp(PacienteMapper pacienteMapper, PacienteRepository pacienteRepository, PsicologoRepository psicologoRepository, EstadoMapper estadoMapper) {
        this.pacienteMapper = pacienteMapper;
        this.pacienteRepository = pacienteRepository;
        this.psicologoRepository = psicologoRepository;
    }

    @Override
    public PacienteDto guardarPaciente(PacienteToSaveDto pacienteDto) {
        Paciente paciente=pacienteMapper.toSaveDtoToEntity(pacienteDto);
        Paciente guardado=pacienteRepository.save(paciente);
        return pacienteMapper.entityToDto(guardado);
    }

    @Override
    public PacienteDto actualizarPaciente(PacienteDto pacienteDto) {
        Paciente pacienteExistente = pacienteRepository.findById(pacienteDto.id())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));

        pacienteExistente.setNombre(pacienteDto.nombre());
        pacienteExistente.setApellido(pacienteDto.apellido());
        pacienteExistente.setEdad(pacienteDto.edad());
        pacienteExistente.setGenero(pacienteDto.genero());
        pacienteExistente.setEmail(pacienteDto.email());
        pacienteExistente.setTelefono(pacienteDto.telefono());
        pacienteExistente.setAcudiente(pacienteDto.acudiente());
        pacienteExistente.setTelEmergencia(pacienteDto.telEmergencia());
        pacienteExistente.setTelAcudiente(pacienteDto.telAcudiente());

        Paciente pacienteActualizado = pacienteRepository.save(pacienteExistente);
        return pacienteMapper.entityToDto(pacienteActualizado);
    }

    @Override
    public PacienteDto buscarPacientePorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
        return pacienteMapper.entityToDto(paciente);
    }

    @Override
    public List<PacienteDto> buscarPacientes() {
        return pacienteRepository.findAll().stream()
                .map(pacienteMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PacienteDto actualizarSesiones(Long idPaciente, Sesion sesion) {
        Paciente paciente=pacienteRepository.findById(idPaciente).orElseThrow(()->new ResourceNotFoundException("Paciente no encontrado"));
        List<Sesion> sesiones=paciente.getSesiones();
        sesiones.add(sesion);
        paciente.setSesiones(sesiones);
        Paciente paciente1=pacienteRepository.save(paciente);
        return pacienteMapper.entityToDto(paciente1);
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
