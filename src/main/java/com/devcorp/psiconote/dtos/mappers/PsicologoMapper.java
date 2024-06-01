package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.PsicologoDto;
import com.devcorp.psiconote.entities.Psicologo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PsicologoMapper {
    PsicologoMapper instancia= Mappers.getMapper(PsicologoMapper.class);
    @Mapping(target = "usuario",ignore = true)
    @Mapping(target = "pacientes",ignore = true)
    Psicologo toPsicologo(PsicologoDto psicologoDto);

    PsicologoDto toPsicologoDto(Psicologo psicologo);
}
