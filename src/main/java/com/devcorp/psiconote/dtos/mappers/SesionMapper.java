package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.SesionDto;
<<<<<<< HEAD
import com.devcorp.psiconote.dtos.SesionToSaveDto;
import com.devcorp.psiconote.entities.Sesion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SesionMapper {
    SesionMapper instancia= Mappers.getMapper(SesionMapper.class);


    default Sesion dtoToEntity(SesionDto sesionDto){
        return new Sesion(sesionDto.id(),
                LocalDateTime.parse(sesionDto.fechaYHora()),
                sesionDto.lugarSesion(),
                PacienteMapper.instancia.dtoToEntity(sesionDto.paciente()),
                PsicologoMapper.instancia.toPsicologo(sesionDto.psicologo()),
                EstadoMapper.instancia.estadoDtoToEntity(sesionDto.estado()));
    };

    SesionDto entityToDto(Sesion sesion);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "paciente",ignore = true)
    @Mapping(target = "psicologo",ignore = true)
    Sesion toSaveDtoToEntity(SesionToSaveDto sesionToSaveDto);
=======
import com.devcorp.psiconote.entities.Sesion;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SesionMapper {
    SesionDto toSesionDto(Sesion sesion);
    Sesion toSesion(SesionDto sesionDto);
>>>>>>> c2b5cd6c6495d6199b7e5e5780e3602de286ce03
}
