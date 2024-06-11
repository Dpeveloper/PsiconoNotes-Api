package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.PacienteDto;
import com.devcorp.psiconote.dtos.PsicologoDto;
import com.devcorp.psiconote.dtos.PsicologoToSaveDto;
import com.devcorp.psiconote.entities.Paciente;
import com.devcorp.psiconote.entities.Psicologo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PsicologoMapper {
    PsicologoMapper instancia= Mappers.getMapper(PsicologoMapper.class);
    @Mapping(target = "usuario",ignore = true)
    @Mapping(target ="pacientes",ignore = true)
    @Mapping(target = "sesiones",ignore = true)
    /*@Mapping(target = "pacientes",expression = "java(pacienteListDtoToEntityList(psicologoDto.pacientes()))")*/
    Psicologo toPsicologo(PsicologoDto psicologoDto);

    @Mapping(target = "usuario",ignore = true)
    default PsicologoDto toPsicologoDto(Psicologo psicologo){
        return new PsicologoDto(
                psicologo.getId(),
                psicologo.getNombre(),
                psicologo.getApellido(),
                psicologo.getEstado(),
                psicologo.getEdad(),
                psicologo.getEmail(),
                psicologo.getTelefono());/*,
                PsicologoMapper.instancia.pacienteListToDtoList(psicologo.getPacientes()));*/
    };

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "pacientes",ignore = true)
    @Mapping(target = "sesiones",ignore = true)
    default Psicologo toSaveDtoToEntity(PsicologoToSaveDto psicologo){
        return Psicologo.builder()
                .nombre(psicologo.nombre())
                .apellido(psicologo.apellido())
                .estado(psicologo.estado())
                .edad(psicologo.edad())
                .telefono(psicologo.telefono())
                .usuario(UsuarioMapper.instancia.toSaveDtoToUsuario(psicologo.usuario()))
                .build();
    };

    default List<Paciente> pacienteListDtoToEntityList(List<PacienteDto> pacienteDtos){
        return pacienteDtos.stream().map(PacienteMapper.instancia::dtoToEntity).collect(Collectors.toList());
    }
    default List<PacienteDto> pacienteListToDtoList(List<Paciente> pacientes){
        return pacientes.stream().map(PacienteMapper.instancia::entityToDto).collect(Collectors.toList());
    }

}
