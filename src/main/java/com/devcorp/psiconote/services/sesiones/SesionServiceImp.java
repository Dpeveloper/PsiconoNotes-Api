package com.devcorp.psiconote.services.sesiones;

import com.devcorp.psiconote.dtos.SesionDto;
import com.devcorp.psiconote.dtos.mappers.SesionMapper;
import com.devcorp.psiconote.entities.Estado;
import com.devcorp.psiconote.entities.Paciente;
import com.devcorp.psiconote.entities.Sesion;
import com.devcorp.psiconote.entities.Usuario;
import com.devcorp.psiconote.repository.PacienteRepository;
import com.devcorp.psiconote.repository.SesionRepository;
import com.devcorp.psiconote.repository.UsuarioRepository;
import com.devcorp.psiconote.services.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SesionServiceImp implements SesionService {

    private final SesionRepository sesionRepository;
    private final UsuarioRepository usuarioRepository;
    private final SesionMapper sesionMapper;
    private final PacienteRepository pacienteRepository;

    @Autowired
    public SesionServiceImp(SesionRepository sesionRepository, UsuarioRepository usuarioRepository, SesionMapper sesionMapper, PacienteRepository pacienteRepository) {
        this.sesionRepository = sesionRepository;
        this.usuarioRepository = usuarioRepository;
        this.sesionMapper = sesionMapper;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public SesionDto agregarSesion(SesionDto sesionDto, Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(()->new ResourceNotFoundException("Paciente no encontrado"));

        Sesion sesion = sesionMapper.toSesion(sesionDto);
        sesion.setPaciente(paciente);
        sesion.setPsicologo(paciente.getPsicologo());
        sesion.setEstado(new Estado("Apartado"));
        Sesion savedSesion = sesionRepository.save(sesion);
        return sesionMapper.toSesionDto(savedSesion);
    }

    @Override
    public List<SesionDto> obtenerTodasLasSesiones() {
        return sesionRepository.findAll().stream()
                .map(sesionMapper::toSesionDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<SesionDto> obtenerProximasSesionesPorPsicologo(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
        return sesionRepository.findSesionByEstadoNombreEstadoAndPsicologoId("Apartada",id)
                .stream().map(sesionMapper::toSesionDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<SesionDto> obtenerProximasSesionesPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return sesionRepository.findSesionByEstadoNombreEstadoAndPacienteId("Apartada",usuarioId).stream()
                .map(sesionMapper::toSesionDto)
                .collect(Collectors.toList());
    }

    @Override
    public SesionDto obtenerSesionPorId(Long id) {
        Sesion sesion = sesionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sesion no encontrado"));
        return sesionMapper.toSesionDto(sesion);
    }

    @Override
    public List<SesionDto> cancelarSesion(Long sesionId) {
        return null;
    }
}
