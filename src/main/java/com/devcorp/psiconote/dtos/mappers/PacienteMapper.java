package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.PacienteDto;
import com.devcorp.psiconote.entities.Paciente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    PacienteDto toPacienteDto(Paciente paciente);
    Paciente toPaciente(PacienteDto pacienteDto);
}
