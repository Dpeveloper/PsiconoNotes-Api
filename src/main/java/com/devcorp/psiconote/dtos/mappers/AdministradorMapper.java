package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.AdministradorDto;
import com.devcorp.psiconote.entities.Administrador;
import org.mapstruct.Mapper;

@Mapper
public interface AdministradorMapper {

    Administrador toAdministrador(AdministradorDto AdministradorDto);

    AdministradorDto toAdministradorDto(Administrador Administrador);
}
