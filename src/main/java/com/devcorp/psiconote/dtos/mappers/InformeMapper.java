package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.InformeDto;
<<<<<<< HEAD
import com.devcorp.psiconote.dtos.InformeToSaveDto;
import com.devcorp.psiconote.entities.Informe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InformeMapper {
    InformeMapper instancia= Mappers.getMapper(InformeMapper.class);

    InformeDto entityToDto(Informe informe);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "psicologo",ignore = true)
    @Mapping(target = "paciente",ignore = true)
    Informe toSaveDtoToEntity(InformeToSaveDto informeToSaveDto);
=======
import com.devcorp.psiconote.entities.Informe;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InformeMapper {

    Informe toInforme(InformeDto informeDto);
    InformeDto toInformeDto(Informe informe);
>>>>>>> c2b5cd6c6495d6199b7e5e5780e3602de286ce03
}
