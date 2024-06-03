package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.UsuarioDto;
import com.devcorp.psiconote.dtos.UsuarioToSaveDto;
import com.devcorp.psiconote.entities.Rol;
import com.devcorp.psiconote.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper{
    UsuarioMapper instancia= Mappers.getMapper(UsuarioMapper.class);

    @Mapping(target = "psicologo",ignore = true)
    @Mapping(target = "administrador",ignore = true)
    @Mapping(target = "paciente",ignore = true)
    @Mapping(target = "roles",ignore = true)
    Usuario toUsuario(UsuarioDto usuario);

    UsuarioDto toUsuarioDto(Usuario usuario);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "psicologo",ignore = true)
    @Mapping(target = "administrador",ignore = true)
    @Mapping(target = "paciente",ignore = true)
    @Mapping(target = "roles",ignore = true)
    Usuario toSaveDtoToUsuario(UsuarioToSaveDto usuario);
}
