package com.devcorp.psiconote.services.psicologo;

import com.devcorp.psiconote.dtos.*;
import com.devcorp.psiconote.dtos.mappers.EstadoMapper;
import com.devcorp.psiconote.dtos.mappers.InformeMapper;
import com.devcorp.psiconote.dtos.mappers.PacienteMapper;
import com.devcorp.psiconote.dtos.mappers.PsicologoMapper;
import com.devcorp.psiconote.entities.Paciente;
import com.devcorp.psiconote.entities.Psicologo;
import com.devcorp.psiconote.repository.InformeRepository;
import com.devcorp.psiconote.repository.PacienteRepository;
import com.devcorp.psiconote.repository.PsicologoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PsicologoServiceImp implements PsicolgoService{

    private final EstadoMapper estadoMapper;
    PsicologoRepository psicologoRepository;
    PacienteRepository pacienteRepository;
    PsicologoMapper psicologoMapper;
    PacienteMapper pacienteMapper;
    InformeRepository informeRepository;
    InformeMapper informeMapper;
    public PsicologoServiceImp(PsicologoRepository psicologoRepository, PsicologoMapper psicologoMapper, PacienteMapper pacienteMapper, InformeRepository informeRepository, InformeMapper informeMapper, PacienteRepository pacienteRepository, EstadoMapper estadoMapper) {
        this.psicologoRepository = psicologoRepository;
        this.psicologoMapper = psicologoMapper;
        this.pacienteMapper = pacienteMapper;
        this.informeRepository = informeRepository;
        this.informeMapper = informeMapper;
        this.pacienteRepository = pacienteRepository;
        this.estadoMapper = estadoMapper;
    }

    @Override
    public PsicologoDto actualizarPsicolgo(PsicologoDto psicologo) {
        Optional<Psicologo> psicologo1 = psicologoRepository.findPsicologoById(psicologo.id());
        if(psicologo1.isPresent()) {
            psicologo1.get().setNombre(psicologo.nombre());
            psicologo1.get().setApellido(psicologo.apellido());
            psicologo1.get().setEdad(psicologo.edad());
            psicologo1.get().setEstado(psicologo.estado());
            psicologo1.get().setTelefono(psicologo.telefono());
            return psicologoMapper.toPsicologoDto(psicologoRepository.save(psicologo1.get()));
        }

        return psicologoMapper.toPsicologoDto(null);
    }

    @Override
    public PacienteDto agregarPaciente(PacienteDto paciente, Long psicologoId) {
        Optional<Psicologo> psicologo = psicologoRepository.findPsicologoById(psicologoId);
        if(psicologo.isPresent()) {

            Paciente p = pacienteMapper.toPaciente(paciente);
            psicologo.get().setPacientes(pacienteRepository.save(p));
            return pacienteMapper.toPacienteDto(p);
        }
        return null;
    }

    @Override
    public PacienteDto actualizarPaciente(PacienteDto paciente,Long psicologoId) {
        Optional<Psicologo> psicologo = psicologoRepository.findPsicologoById(psicologoId);
        if(psicologo.isPresent()) {
            Optional<Paciente> p = psicologo.get().getPacientes().stream()
                    .filter(paciente1 -> paciente1.getId().equals(paciente.id()))
                    .findFirst();
            if(p.isPresent()) {
                return pacienteMapper.toPacienteDto(p.get());
            }
        }
        return null;
    }

    @Override
    public PacienteDto actualizarEstadoPaciente(EstadoToSaveDto estadoPaciente, Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if(paciente.isPresent()) {
            //paciente.get().setEstado(estadoMapper.estadoToSaveDtoToEntity(estadoPaciente));
            return pacienteMapper.toPacienteDto(paciente.get());
        }
        return null;
    }

    @Override
    public List<PacienteDto> buscarTodosLosPacientes(Long psicologoId) {
        Optional<Psicologo> p = psicologoRepository.findById(psicologoId);
        if(p.isPresent()) {
            List<Paciente> pacientes = p.get().getPacientes();
            return pacientes.stream()
                    .map(paciente -> pacienteMapper.toPacienteDto(paciente))
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public InformeDto generarInformePaciente(Long pacienteId, Long psicologoId, InformeDto informeDto) {
        Optional<Psicologo> psicologo = psicologoRepository.findById(psicologoId);
        if(psicologo.isPresent()) {
            Paciente p = psicologo.get().getPacientes()
                    .stream()
                    .filter(paciente -> paciente.getId().equals(pacienteId))
                    .findFirst().get();
            if(p != null) {
               p.setInforme(informeMapper.toInforme(informeDto));
               return informeDto;
            }
        }
        return null;
    }
}
