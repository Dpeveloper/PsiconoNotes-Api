package com.devcorp.psiconote.services.informe;

import com.devcorp.psiconote.dtos.InformeDto;
import com.devcorp.psiconote.dtos.InformeToSaveDto;
import com.devcorp.psiconote.dtos.mappers.InformeMapper;
import com.devcorp.psiconote.entities.Informe;
import com.devcorp.psiconote.repository.InformeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InformeServiceImpl implements InformeService{
    private final InformeMapper informeMapper;
    private final InformeRepository informeRepository;

    public InformeServiceImpl(InformeMapper informeMapper, InformeRepository informeRepository) {
        this.informeMapper = informeMapper;
        this.informeRepository = informeRepository;
    }

    @Override
    public InformeDto guardadInforme(InformeToSaveDto informeToSaveDto) {
        Informe informe=informeMapper.toSaveDtoToEntity(informeToSaveDto);
        Informe guardado=informeRepository.save(informe);
        return informeMapper.entityToDto(guardado);
    }

    @Override
    public InformeDto actualizarInforme(Long id, InformeToSaveDto informeToSaveDto) {
            return informeRepository.findById(id).map(encontrado->{
            encontrado.setResumen(informeToSaveDto.resumen());
            encontrado.setObjetivos(informeToSaveDto.objetivos());
            encontrado.setTrabajoRealizado(informeToSaveDto.trabajoRealizado());
            encontrado.setObservaciones(informeToSaveDto.observaciones());
            encontrado.setRespuestasEstudiante(informeToSaveDto.respuestasEstudiante());
            encontrado.setConclusiones(informeToSaveDto.conclusiones());
            encontrado.setPlanAccion(informeToSaveDto.planAccion());
            encontrado.setNotasAdicionales(informeToSaveDto.notasAdicionales());

            Informe guardado=informeRepository.save(encontrado);
            return informeMapper.entityToDto(guardado);
        }).orElseThrow(()->new RuntimeException("Informe no encontrado para actualizar"));
    }

    @Override
    public InformeDto obtenerInformePorId(Long id) {
        Informe informe=informeRepository.findById(id).orElseThrow(()->new RuntimeException("Informe no encontrado"));
        return informeMapper.entityToDto(informe);
    }

    @Override
    public List<InformeDto> obtenerTodosInformes() {
        List<InformeDto> informeDtos=informeRepository.findAll().stream().map(informeMapper::entityToDto)
                .collect(Collectors.toList());
        return informeDtos;
    }

    @Override
    public List<InformeDto> obtenerInformesPorPaciente(Long idPaciente) {
        List<InformeDto> informeDtos=informeRepository.findByPaciente(idPaciente).stream().map(informeMapper::entityToDto)
                .collect(Collectors.toList());
        return informeDtos;
    }

    @Override
    public void eliminarInforme(Long id) {
        Informe informe=informeRepository.findById(id).orElseThrow(()->new RuntimeException("Informe no encontrado para eliminar"));
        informeRepository.deleteById(id);
    }
}
