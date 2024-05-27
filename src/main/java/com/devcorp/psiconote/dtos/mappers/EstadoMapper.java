package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.EstadoDto;
import com.devcorp.psiconote.dtos.EstadoToSaveDto;
import com.devcorp.psiconote.entities.Estado;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EstadoMapper {
    EstadoMapper instancia= Mappers.getMapper(EstadoMapper.class);

    Estado estadoToSaveDtoToEntity(EstadoToSaveDto estadoToSaveDto);
    EstadoDto entityToEstadoDto(Estado estado);
    Estado estadoDtoToEntity(EstadoDto estadoDto);
}
