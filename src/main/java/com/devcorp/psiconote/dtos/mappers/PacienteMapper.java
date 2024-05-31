package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.PacienteDto;
import com.devcorp.psiconote.entities.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PacienteMapper {

    PacienteDto toPacienteDto(Paciente paciente);
    Paciente toPaciente(PacienteDto pacienteDto);
}
