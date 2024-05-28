package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.UsuarioDto;
import com.devcorp.psiconote.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper
public interface UsuarioMapper{
    UsuarioDto toUsuarioDto(Usuario usuario);
    Usuario toUsuario(UsuarioDto usuarioDto);
}
