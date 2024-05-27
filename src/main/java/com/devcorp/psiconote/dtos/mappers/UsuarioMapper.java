package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper
public interface UsuarioMapper{
    Usuario toUsuarioDto(Usuario usuario);
    Usuario toUsuario(Usuario usuario);
}
