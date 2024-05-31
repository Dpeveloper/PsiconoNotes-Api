package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.UsuarioDto;
import com.devcorp.psiconote.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper{
    UsuarioDto toUsuarioDto(Usuario usuario);
    Usuario toUsuario(UsuarioDto usuarioDto);
}
