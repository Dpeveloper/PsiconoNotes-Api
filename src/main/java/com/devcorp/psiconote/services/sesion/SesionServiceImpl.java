package com.devcorp.psiconote.services.sesion;

import com.devcorp.psiconote.dtos.InformeToSaveDto;
import com.devcorp.psiconote.dtos.SesionDto;
import com.devcorp.psiconote.dtos.SesionToSaveDto;
import com.devcorp.psiconote.dtos.mappers.EstadoMapper;
import com.devcorp.psiconote.dtos.mappers.PsicologoMapper;
import com.devcorp.psiconote.dtos.mappers.SesionMapper;
import com.devcorp.psiconote.entities.Estado;
import com.devcorp.psiconote.entities.Informe;
import com.devcorp.psiconote.entities.Sesion;
import com.devcorp.psiconote.entities.SesionCancelada;
import com.devcorp.psiconote.repository.*;
import com.devcorp.psiconote.services.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SesionServiceImpl implements SesionService{
    private final SesionMapper sesionMapper;
    private final SesionRepository sesionRepository;
    private final PsicologoRepository psicologoRepository;
    private final PacienteRepository pacienteRepository;
    private final InformeRepository informeRepository;
    private final SesionCanceladaRepository sesionCanceladaRepository;

    public SesionServiceImpl(SesionMapper sesionMapper, SesionRepository sesionRepository,
                             PsicologoRepository psicologoRepository, PacienteRepository pacienteRepository,
                             InformeRepository informeRepository, SesionCanceladaRepository sesionCanceladaRepository) {
        this.sesionMapper = sesionMapper;
        this.sesionRepository = sesionRepository;
        this.psicologoRepository=psicologoRepository;
        this.pacienteRepository=pacienteRepository;
        this.informeRepository=informeRepository;
        this.sesionCanceladaRepository = sesionCanceladaRepository;
    }

    @Override
    public SesionDto guardarSesion(SesionToSaveDto sesionToSaveDto) {
        Sesion sesion=sesionMapper.toSaveDtoToEntity(sesionToSaveDto);
        sesion.setPsicologo(psicologoRepository.findById(sesionToSaveDto.idPsicologo()).get());
        sesion.setPaciente(pacienteRepository.findById(sesionToSaveDto.idPaciente()).get());
        sesion.setEstado(EstadoMapper.instancia.estadoToSaveDtoToEntity(sesionToSaveDto.estado()));
        Sesion guardada=sesionRepository.save(sesion);
        return sesionMapper.entityToDto(sesion);
    }

    @Override
    public SesionDto actualizarSesion(Long id, SesionToSaveDto sesionToSaveDto) {
        return sesionRepository.findById(id).map(encontrada->{
            encontrada.setFechaYHora(sesionMapper.toSaveDtoToEntity(sesionToSaveDto).getFechaYHora());
            encontrada.setLugarSesion(sesionToSaveDto.lugarSesion());
            encontrada.setEstado(sesionMapper.toSaveDtoToEntity(sesionToSaveDto).getEstado());

            Sesion actualizada=sesionRepository.save(encontrada);
            return sesionMapper.entityToDto(actualizada);
        }).orElseThrow(()->new RuntimeException("Sesion no encontrada"));
    }

    @Override
    public SesionDto obtenerSesionPorId(Long id) {
        return sesionMapper.entityToDto(sesionRepository.findById(id).orElseThrow(()->new RuntimeException("Sesion no encontrada")));
    }

    @Override
    public List<SesionDto> obtenerSesionesPorEstado(String nombreEstado) {
        List<SesionDto> sesionDtos=sesionRepository.findByEstado(nombreEstado).stream()
                .map(sesionMapper::entityToDto).collect(Collectors.toList());
        return sesionDtos;
    }

    @Override
    public List<SesionDto> obtenerSesionPorFechaYHora(String fechaYHora) {

        List<SesionDto> sesionDtos=sesionRepository.findByFechaYHora(fechaStringALocalDateTime(fechaYHora))
                .stream().map(sesionMapper::entityToDto).collect(Collectors.toList());
        return sesionDtos;
    }

    @Override
    public List<SesionDto> obtenerSesionesPorPsicologo(Long idPsicologo) {
        List<SesionDto> sesionDtos=sesionRepository.findByPsicologo(idPsicologo).stream()
                .map(sesionMapper::entityToDto).collect(Collectors.toList());
        return sesionDtos;
    }

    @Override
    public List<SesionDto> obtenerSesionesPorPaciente(Long idPaciente) {
        List<SesionDto> sesionDtos=sesionRepository.findByPaciente(idPaciente).stream()
                .map(sesionMapper::entityToDto).collect(Collectors.toList());
        return sesionDtos;
    }

    @Override
    public List<SesionDto> obtenerSesiones() {
        List<SesionDto> sesionDtos=sesionRepository.findAll().stream()
                .map(sesionMapper::entityToDto).collect(Collectors.toList());
        return sesionDtos;
    }

    @Override
    public SesionDto actualizarInformeSesion(Long idSesion,  Long idInforme) {
        Sesion sesion=sesionRepository.findById(idSesion).get();
        sesion.setInforme(informeRepository.findById(idInforme).get());
        Sesion sesionActualizada=sesionRepository.save(sesion);
        return sesionMapper.entityToDto(sesionActualizada);
    }

    @Override
    public void eliminarSesion(Long id) {
        sesionRepository.findById(id).orElseThrow(()->new RuntimeException("Sesión no encontrada para eliminar"));
        sesionRepository.deleteById(id);
    }

    @Override
    public SesionDto reagendarSesion(Long idSesion, LocalDateTime fecha, String lugarSesion) {
        Sesion sesion = sesionRepository.findById(idSesion)
                .orElseThrow(() -> new RuntimeException("Sesión no encontrada para reagendar"));

        sesion.setLugarSesion(lugarSesion);

        if(sesionRepository.findByFechaYHora(fecha).isEmpty()){
            sesion.setFechaYHora(fecha);
            sesion.setEstado(new Estado("Agendada"));
            return sesionMapper.entityToDto(sesionRepository.save(sesion));
        }
        throw  new RuntimeException("El psicolog ya tiene una sesión a esa hora");
    }

    @Override
    public SesionDto cancelarSesion(Long sesionId, String notificacion) {
        Sesion sesion = sesionRepository.findById(sesionId)
                .orElseThrow(() -> new ResourceNotFoundException("Sesion no encontrado"));

        sesion.setEstado(new Estado("Cancelado"));
        sesion.setNotificacion(notificacion);
        Sesion sesionCancelada = sesionRepository.save(sesion);

        sesionCanceladaRepository.save(new SesionCancelada(null,
                sesion.getFechaYHora(),
                sesion.getLugarSesion(),
                sesion.getPaciente(),
                sesion.getPsicologo(),
                sesion.getEstado(),
                sesion.getNotificacion()));

        return sesionMapper.entityToDto(sesionCancelada);

    }

    @Override
    public Object solicitarCancelarSesion(Long id, String notificacion) {
        Sesion sesion = sesionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesión no encontrada"));

        sesion.setNotificacion(notificacion);
        return sesionRepository.save(sesion);
    }

    @Override
    public Object solicitarReagendarSesion(Long id, String notificacion) {
        Sesion sesion = sesionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesión no encontrada"));

        sesion.setNotificacion(notificacion);
        return sesionRepository.save(sesion);
    }

    private LocalDateTime fechaStringALocalDateTime(String fechaYHora){
        LocalDateTime fechaYHoraLocal=LocalDateTime.parse(fechaYHora);
        return fechaYHoraLocal;
    }


}
