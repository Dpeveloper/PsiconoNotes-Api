package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.PsicologoDto;
import com.devcorp.psiconote.entities.Psicologo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PsicologoMapper {
    Psicologo toPsicologo(PsicologoDto psicologoDto);

    PsicologoDto toPsicologoDto(Psicologo psicologo);
}
