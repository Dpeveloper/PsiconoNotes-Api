package com.devcorp.psiconote.services.sesion;

import com.devcorp.psiconote.dtos.SesionDto;
import com.devcorp.psiconote.dtos.SesionToSaveDto;
import com.devcorp.psiconote.dtos.mappers.SesionMapper;
import com.devcorp.psiconote.entities.Sesion;
import com.devcorp.psiconote.repository.SesionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SesionServiceImpl implements SesionService{
    private final SesionMapper sesionMapper;
    private final SesionRepository sesionRepository;

    public SesionServiceImpl(SesionMapper sesionMapper, SesionRepository sesionRepository) {
        this.sesionMapper = sesionMapper;
        this.sesionRepository = sesionRepository;
    }

    @Override
    public SesionDto guardarSesion(SesionToSaveDto sesionToSaveDto) {
        Sesion sesion=sesionMapper.toSaveDtoToEntity(sesionToSaveDto);
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
    public void eliminarSesion(Long id) {
        sesionRepository.findById(id).orElseThrow(()->new RuntimeException("Sesión no encontrada para eliminar"));
        sesionRepository.deleteById(id);
    }

    private LocalDateTime fechaStringALocalDateTime(String fechaYHora){
        LocalDateTime fechaYHoraLocal=LocalDateTime.parse(fechaYHora);
        return fechaYHoraLocal;
    }
}
