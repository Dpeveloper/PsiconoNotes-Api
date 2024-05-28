package com.devcorp.psiconote.dtos.mappers;

import com.devcorp.psiconote.dtos.InformeDto;
import com.devcorp.psiconote.entities.Informe;

public interface InformeMapper {

    Informe toInforme(InformeDto informeDto);
    InformeDto toInformeDto(Informe informe);
}
