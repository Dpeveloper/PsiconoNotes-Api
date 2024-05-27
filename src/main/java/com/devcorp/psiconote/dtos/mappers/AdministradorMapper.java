package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.AdministradorDto;
import com.devcorp.psiconote.entities.Administrador;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AdministradorMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "usuario",ignore = true)
    @Mapping(target = "sedes",ignore = true)
    Administrador toAdministrador(AdministradorDto AdministradorDto);

    AdministradorDto toAdministradorDto(Administrador Administrador);
}
