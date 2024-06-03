package com.devcorp.psiconote.services.sesion;

import com.devcorp.psiconote.dtos.InformeToSaveDto;
import com.devcorp.psiconote.dtos.SesionDto;
import com.devcorp.psiconote.dtos.SesionToSaveDto;
import com.devcorp.psiconote.dtos.mappers.EstadoMapper;
import com.devcorp.psiconote.dtos.mappers.PsicologoMapper;
import com.devcorp.psiconote.dtos.mappers.SesionMapper;
import com.devcorp.psiconote.entities.Informe;
import com.devcorp.psiconote.entities.Sesion;
import com.devcorp.psiconote.repository.InformeRepository;
import com.devcorp.psiconote.repository.PacienteRepository;
import com.devcorp.psiconote.repository.PsicologoRepository;
import com.devcorp.psiconote.repository.SesionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SesionServiceImpl implements SesionService{
    private final SesionMapper sesionMapper;
    private final SesionRepository sesionRepository;
    private final PsicologoRepository psicologoRepository;
    private final PacienteRepository pacienteRepository;
    private final InformeRepository informeRepository;
    public SesionServiceImpl(SesionMapper sesionMapper, SesionRepository sesionRepository,
                             PsicologoRepository psicologoRepository, PacienteRepository pacienteRepository,
                             InformeRepository informeRepository) {
        this.sesionMapper = sesionMapper;
        this.sesionRepository = sesionRepository;
        this.psicologoRepository=psicologoRepository;
        this.pacienteRepository=pacienteRepository;
        this.informeRepository=informeRepository;
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
            encontrada.setFecha(sesionMapper.toSaveDtoToEntity(sesionToSaveDto).getFecha());
            encontrada.setHoraInicio(sesionMapper.toSaveDtoToEntity(sesionToSaveDto).getHoraInicio());
            encontrada.setHoraFinal(sesionMapper.toSaveDtoToEntity(sesionToSaveDto).getHoraFinal());
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
        List<SesionDto> sesionDtos=sesionRepository.findByEstado(nombreEstado.toLowerCase()).stream()
                .map(sesionMapper::entityToDto).collect(Collectors.toList());
        return sesionDtos;
    }

    @Override
    public List<SesionDto> obtenerSesionPorFecha(String fecha) {
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<SesionDto> sesionDtos=sesionRepository.findByFecha(LocalDate.parse(fecha,formatter))
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
    public SesionDto actualizarEstadoSesion(String nombreEstado,Long idSesion) {
        SesionDto sesionDto=sesionMapper.entityToDto(sesionRepository.updateEstadoSesion(nombreEstado,idSesion));
        return sesionDto;
    }

    @Override
    public void eliminarSesion(Long id) {
        sesionRepository.findById(id).orElseThrow(()->new RuntimeException("Sesión no encontrada para eliminar"));
        sesionRepository.deleteById(id);
    }


}
