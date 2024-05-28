package com.devcorp.psiconote.services.psicologo;

import com.devcorp.psiconote.dtos.InformeDto;
import com.devcorp.psiconote.dtos.PacienteDto;
import com.devcorp.psiconote.dtos.PsicologoDto;
import com.devcorp.psiconote.dtos.mappers.InformeMapper;
import com.devcorp.psiconote.dtos.mappers.PacienteMapper;
import com.devcorp.psiconote.dtos.mappers.PsicologoMapper;
import com.devcorp.psiconote.entities.Informe;
import com.devcorp.psiconote.entities.Paciente;
import com.devcorp.psiconote.entities.Psicologo;
import com.devcorp.psiconote.repository.InformeRepository;
import com.devcorp.psiconote.repository.PsicologoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PsicologoServiceImp implements PsicolgoService{

    PsicologoRepository psicologoRepository;
    PsicologoMapper psicologoMapper;
    PacienteMapper pacienteMapper;
    InformeRepository informeRepository;
    InformeMapper informeMapper;
    public PsicologoServiceImp(PsicologoRepository psicologoRepository, PsicologoMapper psicologoMapper,PacienteMapper pacienteMapper, InformeRepository informeRepository,InformeMapper informeMapper) {
        this.psicologoRepository = psicologoRepository;
        this.psicologoMapper = psicologoMapper;
        this.pacienteMapper = pacienteMapper;
        this.informeRepository = informeRepository;
        this.informeMapper = informeMapper;
    }

    @Override
    public PsicologoDto actualizarPsicolgo(PsicologoDto psicologo, Long id) {
        Optional<Psicologo> psicologo1 = psicologoRepository.findPsicologoById(id);
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
    public PacienteDto agregarPaciente(PacienteDto pacientes) {
        Optional<Psicologo> psicologo = psicologoRepository.findPsicologoById(pacientes.id());
        if(psicologo.isPresent()) {
            Paciente paciente = pacienteMapper.toPaciente(pacientes);
            psicologo.get().setPacientes(paciente);
            return pacienteMapper.toPacienteDto(paciente);
        }
        return pacientes;
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
    public PacienteDto actualizarEstadoPaciente(String estadoPaciente, Long id) {
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

    @Override
    public InformeDto crearInforme(InformeDto informe) {
        return null;
    }

    @Override
    public InformeDto actualizarInforme(InformeDto informe) {

        return null;
    }

    @Override
    public List<InformeDto> buscarTodosLosInformes() {
        return List.of();
    }

    @Override
    public List<InformeDto> buscarInformesDelPaciente(Long id) {
        return List.of();
    }
}
