package com.devcorp.psiconote.services.psicologo;

import com.devcorp.psiconote.dtos.*;
import com.devcorp.psiconote.dtos.mappers.InformeMapper;
import com.devcorp.psiconote.dtos.mappers.PacienteMapper;
import com.devcorp.psiconote.dtos.mappers.PsicologoMapper;
import com.devcorp.psiconote.entities.Informe;
import com.devcorp.psiconote.entities.Paciente;
import com.devcorp.psiconote.entities.Psicologo;
import com.devcorp.psiconote.entities.Sesion;
import com.devcorp.psiconote.repository.InformeRepository;
import com.devcorp.psiconote.repository.PacienteRepository;
import com.devcorp.psiconote.repository.PsicologoRepository;
import com.devcorp.psiconote.services.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PsicologoServiceImp implements PsicologoService {

    private final PsicologoRepository psicologoRepository;
    private final PacienteRepository pacienteRepository;
    private final PsicologoMapper psicologoMapper;
    private final PacienteMapper pacienteMapper;
    private final InformeRepository informeRepository;
    private final InformeMapper informeMapper;

    @Autowired
    public PsicologoServiceImp(PsicologoRepository psicologoRepository, PsicologoMapper psicologoMapper, PacienteMapper pacienteMapper, InformeRepository informeRepository, InformeMapper informeMapper, PacienteRepository pacienteRepository) {
        this.psicologoRepository = psicologoRepository;
        this.psicologoMapper = psicologoMapper;
        this.pacienteMapper = pacienteMapper;
        this.informeRepository = informeRepository;
        this.informeMapper = informeMapper;
        this.pacienteRepository = pacienteRepository;
    }


    @Override
    public PsicologoDto crearPsicologo(PsicologoToSaveDto psicologo) {
        Psicologo psicologoEntidad=psicologoMapper.toSaveDtoToEntity(psicologo);
        Psicologo psicologoGuardar=psicologoRepository.save(psicologoEntidad);
        return psicologoMapper.toPsicologoDto(psicologoGuardar);
    }

    @Override
    public PsicologoDto obtenerPsicologoPorId(Long id) {
        Psicologo psicologo=psicologoRepository.findById(id).get();
        return psicologoMapper.toPsicologoDto(psicologo);
    }

    @Override
    public PsicologoDto actualizarPsicologo(PsicologoDto psicologoDto) {
        Psicologo psicologoExistente = psicologoRepository.findById(psicologoDto.id())
                .orElseThrow(() -> new ResourceNotFoundException("Psicólogo no encontrado"));

        psicologoExistente.setNombre(psicologoDto.nombre());
        psicologoExistente.setApellido(psicologoDto.apellido());
        psicologoExistente.setEdad(psicologoDto.edad());
        psicologoExistente.setEstado(psicologoDto.estado());
        psicologoExistente.setTelefono(psicologoDto.telefono());

        Psicologo psicologoActualizado = psicologoRepository.save(psicologoExistente);
        return psicologoMapper.toPsicologoDto(psicologoActualizado);
    }

    @Override
    public List<PsicologoDto> buscarTodosLosPsicologos() {
        List<Psicologo> psicologos = psicologoRepository.findAll();
        return psicologos.stream().map(psicologoMapper::toPsicologoDto).collect(Collectors.toList());
    }

    @Override
    public List<PacienteDto> buscarTodosLosPacientes(Long psicologoId) {
        Psicologo psicologo = psicologoRepository.findById(psicologoId)
                .orElseThrow(() -> new ResourceNotFoundException("Psicólogo no encontrado"));

        List<Paciente> pacientes = psicologo.getPacientes();
        return pacientes.stream()
                .map(pacienteMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PsicologoDto actualizarSesiones(Long idPsicologo, Sesion sesion) {
        Psicologo psicologo = psicologoRepository.findById(idPsicologo).orElseThrow(() -> new ResourceNotFoundException("Psicologo no encontrado"));
        List<Sesion> sesiones = psicologo.getSesiones();
        sesiones.add(sesion);
        psicologo.setSesiones(sesiones);
        Psicologo psicologo1 = psicologoRepository.save(psicologo);
        return psicologoMapper.toPsicologoDto(psicologo1);
    }

    public List<PsicologoDto> buscarPsicologoPorNombre(String nombrePsicologo) {
        List<Psicologo> psicologos = psicologoRepository.findByNombre(nombrePsicologo);
        return psicologos.stream().map(psicologoMapper::toPsicologoDto).collect(Collectors.toList());
    }

    @Override
    public InformeDto generarInformePaciente(Long pacienteId, InformeToSaveDto informeToSaveDto) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));

        Informe informe = informeMapper.toSaveDtoToEntity(informeToSaveDto);
        informe.setPaciente(paciente);
        informe.setPsicologo(paciente.getPsicologo());

        Informe informeGuardado = informeRepository.save(informe);
        return informeMapper.entityToDto(informeGuardado);
    }
}
