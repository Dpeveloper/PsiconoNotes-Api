package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.PacienteDto;
import com.devcorp.psiconote.entities.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PacienteMapper {
    PacienteMapper instancia = Mappers.getMapper(PacienteMapper.class);

    @Mapping(target = "psicologo", ignore = true)
    @Mapping(target = "grado", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    Paciente dtoToEntity(PacienteDto pacienteDto);
    PacienteDto toPacienteDto(Paciente paciente);
}
