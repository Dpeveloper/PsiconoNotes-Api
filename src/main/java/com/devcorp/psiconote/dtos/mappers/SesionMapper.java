package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.SesionDto;
import com.devcorp.psiconote.dtos.SesionToSaveDto;
import com.devcorp.psiconote.entities.Sesion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SesionMapper {
    SesionMapper instancia= Mappers.getMapper(SesionMapper.class);


    default Sesion dtoToEntity(SesionDto sesionDto){
        return new Sesion(sesionDto.id(),
                sesionDto.fecha(),
                sesionDto.horaInicio(),
                sesionDto.horaFinal(),
                sesionDto.lugarSesion(),
                PacienteMapper.instancia.dtoToEntity(sesionDto.paciente()),
                PsicologoMapper.instancia.toPsicologo(sesionDto.psicologo()),
                EstadoMapper.instancia.estadoDtoToEntity(sesionDto.estado()),
                InformeMapper.instancia.dtoToEntity(sesionDto.informe()));
    };

    SesionDto entityToDto(Sesion sesion);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "paciente",ignore = true)
    @Mapping(target = "psicologo",ignore = true)
    @Mapping(target = "informe",ignore = true)
    default Sesion toSaveDtoToEntity(SesionToSaveDto sesionToSaveDto){
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("HH:mm:ss");
        return Sesion.builder()
                        .fecha(LocalDate.parse(sesionToSaveDto.fecha(),dateTimeFormatter))
                        .horaInicio(LocalTime.parse(sesionToSaveDto.horaInicio(),timeFormatter))
                        .horaFinal(LocalTime.parse(sesionToSaveDto.horaFinal(),timeFormatter))
                        .lugarSesion(sesionToSaveDto.lugarSesion())
                        .estado(EstadoMapper.instancia.estadoToSaveDtoToEntity(sesionToSaveDto.estado()))
                    .build();
    };
}
