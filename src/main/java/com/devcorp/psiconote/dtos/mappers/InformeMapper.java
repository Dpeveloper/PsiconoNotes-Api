package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.InformeDto;
import com.devcorp.psiconote.entities.Informe;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InformeMapper {

    Informe toInforme(InformeDto informeDto);
    InformeDto toInformeDto(Informe informe);
}
