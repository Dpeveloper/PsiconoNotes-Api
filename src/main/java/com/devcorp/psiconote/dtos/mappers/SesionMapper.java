package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.SesionDto;
import com.devcorp.psiconote.entities.Sesion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SesionMapper {
    SesionDto toSesionDto(Sesion sesion);
    Sesion toSesion(SesionDto sesionDto);
}
