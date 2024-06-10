package com.devcorp.psiconote.services.estado;

import com.devcorp.psiconote.dtos.EstadoDto;
import com.devcorp.psiconote.dtos.EstadoToSaveDto;
import com.devcorp.psiconote.dtos.mappers.EstadoMapper;
import com.devcorp.psiconote.entities.Estado;
import com.devcorp.psiconote.repository.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoServiceImpl implements EstadoService{
    private final EstadoMapper estadoMapper;
    private final EstadoRepository estadoRepository;

    public EstadoServiceImpl(EstadoMapper estadoMapper, EstadoRepository estadoRepository) {
        this.estadoMapper = estadoMapper;
        this.estadoRepository = estadoRepository;
    }

    @Override
    public EstadoDto guardarEstado(EstadoToSaveDto estado) {
        estado.nombreEstado().toLowerCase();
        Estado estadoEntity=estadoMapper.estadoToSaveDtoToEntity(estado);
        Estado estadoGuardado=estadoRepository.save(estadoEntity);

        return estadoMapper.entityToEstadoDto(estadoGuardado);
    }

    @Override
    public EstadoDto actualizarEstado(Long id, EstadoToSaveDto estado) {
        return estadoRepository.findById(id).map(estadoEncontrado-> {
            estadoEncontrado.setNombreEstado(estado.nombreEstado());
            Estado estadoActualizado = estadoRepository.save(estadoEncontrado);

            return estadoMapper.entityToEstadoDto(estadoActualizado);
        }).orElseThrow(()->new RuntimeException("Estado no encontrado"));
    }

    @Override
    public EstadoDto buscarEstadoId(Long id) throws Exception {
        return estadoMapper.entityToEstadoDto(estadoRepository.findById(id).orElseThrow(()->new Exception("Estado no encontrado")));
    }

    @Override
    public EstadoDto buscarEstadoPorNombre(String nombreEstado) throws Exception {
        return estadoMapper.entityToEstadoDto(estadoRepository.findByNombreEstado(nombreEstado.toLowerCase()).orElseThrow(()->new Exception("Estado no encontrado")));
    }

    @Override
    public List<EstadoDto> buscarTodosEstados() {
        List<EstadoDto> estadoDtos=estadoRepository.findAll().stream().map(estadoMapper::entityToEstadoDto).collect(Collectors.toList());
        return estadoDtos;
    }

    @Override
    public void eliminarEstado(Long id) {
        Estado estadoEncontrado=estadoRepository.findById(id).orElseThrow(()->new RuntimeException("Estado no encontrado"));
        estadoRepository.deleteById(id);
    }
}