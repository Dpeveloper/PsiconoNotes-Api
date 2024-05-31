package com.devcorp.psiconote.services.informe;

import com.devcorp.psiconote.dtos.InformeDto;
import com.devcorp.psiconote.dtos.mappers.InformeMapper;
import com.devcorp.psiconote.entities.Informe;
import com.devcorp.psiconote.entities.Paciente;
import com.devcorp.psiconote.repository.InformeRepository;
import com.devcorp.psiconote.repository.PacienteRepository;
import com.devcorp.psiconote.services.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InformeServiceImp implements InformeService {
    private final InformeRepository informeRepository;
    private final PacienteRepository pacienteRepository;
    private final InformeMapper informeMapper;

    @Autowired
    public InformeServiceImp(InformeRepository informeRepository, PacienteRepository pacienteRepository, InformeMapper informeMapper) {
        this.informeRepository = informeRepository;
        this.pacienteRepository = pacienteRepository;
        this.informeMapper = informeMapper;
    }

    @Override
    public InformeDto registrarInforme(Long pacienteId, InformeDto informeDto) {
        Optional<Paciente> pacienteOpt = pacienteRepository.findById(pacienteId);
        if (pacienteOpt.isPresent()) {
            Informe informe = informeMapper.toInforme(informeDto);
            informe.setPaciente(pacienteOpt.get());
            Informe savedInforme = informeRepository.save(informe);
            return informeMapper.toInformeDto(savedInforme);
        }
        throw new ResourceNotFoundException("Paciente no encontrado");
    }

    @Override
    public InformeDto actualizarInforme(Long pacienteId, InformeDto informeDto) {
        Optional<Informe> informeOpt = informeRepository.findById(informeDto.id());
        if (informeOpt.isPresent()) {
            Informe informe = informeOpt.get();
            informe.setResumen(informeDto.resumen());
            informe.setObjetivos(informeDto.objetivos());
            informe.setTrabajoRealizado(informeDto.trabajoRealizado());
            informe.setObservaciones(informeDto.observaciones());
            informe.setRespuestasEstudiante(informeDto.respuestasEstudiante());
            informe.setConclusiones(informeDto.conclusiones());
            informe.setPlanAccion(informeDto.planAccion());
            informe.setNotasAdicionales(informeDto.notasAdicionales());
            Informe updatedInforme = informeRepository.save(informe);
            return informeMapper.toInformeDto(updatedInforme);
        }
        throw new ResourceNotFoundException("Informe no encontrado");
    }

    @Override
    public InformeDto obtenerInforme(Long pacienteId, Long informeId) {
        Optional<Informe> informeOpt = informeRepository.findById(informeId);
        if (informeOpt.isPresent() && informeOpt.get().getPaciente().getId().equals(pacienteId)) {
            return informeMapper.toInformeDto(informeOpt.get());
        }
        throw new ResourceNotFoundException("Informe no encontrado");
    }

    @Override
    public List<InformeDto> buscarTodosLosInformes(Long pacienteId) {
        List<Informe> informes = informeRepository.findByPaciente(pacienteId);
        return informes.stream()
                .map(informeMapper::toInformeDto)
                .collect(Collectors.toList());
    }
}
