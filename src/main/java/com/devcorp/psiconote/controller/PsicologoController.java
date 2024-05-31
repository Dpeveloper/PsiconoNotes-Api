package com.devcorp.psiconote.controller;

import com.devcorp.psiconote.dtos.EstadoDto;
import com.devcorp.psiconote.dtos.EstadoToSaveDto;
import com.devcorp.psiconote.dtos.PacienteDto;
import com.devcorp.psiconote.dtos.PsicologoDto;
import com.devcorp.psiconote.services.psicologo.PsicologoServiceImp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/psicoNote/v1/psicologo")
public class PsicologoController {
    public final PsicologoServiceImp psicologoServiceImp;

    public PsicologoController(PsicologoServiceImp psicologoServiceImp) {
        this.psicologoServiceImp = psicologoServiceImp;
    }

    @PostMapping
    PsicologoDto actualizarPsicologo(@RequestBody PsicologoDto psicologo) {
        return psicologoServiceImp.actualizarPsicolgo(psicologo);
    }

    @PostMapping
    PacienteDto agregarPaciente(@RequestBody Long psicologoId, PacienteDto pacienteDto) {
        return psicologoServiceImp.agregarPaciente(pacienteDto,psicologoId);
    }

    @PostMapping
    PacienteDto actualizarPaciente(@RequestBody PacienteDto pacienteDto, Long psicologoId) {
        return psicologoServiceImp.actualizarPaciente(pacienteDto, psicologoId);
    }

    @PostMapping
    EstadoDto actualizarEstado(@RequestBody EstadoToSaveDto estadoToSaveDto, Long pacienteId) {
        return psicologoServiceImp.actualizarEstadoPaciente(estadoToSaveDto,pacienteId);
    }
}
