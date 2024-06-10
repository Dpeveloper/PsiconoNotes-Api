package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.RolToSaveDto;
import com.devcorp.psiconote.entities.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RolMapper {
    RolMapper instancia= Mappers.getMapper(RolMapper.class);

    @Mapping(target = "id",ignore = true)
    Rol toSaveDtoToEntity(RolToSaveDto rol);
}
