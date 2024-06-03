package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.PsicologoDto;
import com.devcorp.psiconote.entities.Psicologo;
import org.mapstruct.Mapper;
<<<<<<< HEAD
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
=======
import org.mapstruct.MappingConstants;
>>>>>>> c2b5cd6c6495d6199b7e5e5780e3602de286ce03

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PsicologoMapper {
    PsicologoMapper instancia= Mappers.getMapper(PsicologoMapper.class);
    @Mapping(target = "usuario",ignore = true)
    @Mapping(target = "pacientes",ignore = true)
    Psicologo toPsicologo(PsicologoDto psicologoDto);

    PsicologoDto toPsicologoDto(Psicologo psicologo);
}
