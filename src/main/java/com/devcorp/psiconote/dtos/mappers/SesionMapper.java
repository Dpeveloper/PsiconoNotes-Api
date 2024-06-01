package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.SesionDto;
import com.devcorp.psiconote.dtos.SesionToSaveDto;
import com.devcorp.psiconote.entities.Sesion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SesionMapper {
    SesionMapper instancia= Mappers.getMapper(SesionMapper.class);


    default Sesion dtoToEntity(SesionDto sesionDto){
        return new Sesion(sesionDto.id(),
                LocalDateTime.parse(sesionDto.fechaYHora()),
                sesionDto.lugarSesion(),
                PacienteMapper.instancia.dtoToEntity(sesionDto.paciente()),
                PsicologoMapper.instancia.toPsicologo(sesionDto.psicologo()),
                EstadoMapper.instancia.estadoDtoToEntity(sesionDto.estado()),
                InformeMapper.instancia.dtoToEntity(sesionDto.informe()));
    };

    SesionDto entityToDto(Sesion sesion);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "paciente",ignore = true)
    @Mapping(target = "psicologo",ignore = true)
    @Mapping(target = "informe",ignore = true)
    Sesion toSaveDtoToEntity(SesionToSaveDto sesionToSaveDto);
}
