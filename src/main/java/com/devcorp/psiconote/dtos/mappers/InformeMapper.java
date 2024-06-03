package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.InformeDto;
import com.devcorp.psiconote.dtos.InformeToSaveDto;
import com.devcorp.psiconote.entities.Informe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InformeMapper {
    InformeMapper instancia= Mappers.getMapper(InformeMapper.class);

    InformeDto entityToDto(Informe informe);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "psicologo",ignore = true)
    @Mapping(target = "paciente",ignore = true)
    Informe toSaveDtoToEntity(InformeToSaveDto informeToSaveDto);

    @Mapping(target = "psicologo",ignore = true)
    @Mapping(target = "paciente",ignore = true)
    Informe dtoToEntity(InformeDto informeDto);
}
