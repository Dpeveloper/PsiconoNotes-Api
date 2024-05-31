package com.devcorp.psiconote.controller;

import com.devcorp.psiconote.dtos.InformeDto;
import com.devcorp.psiconote.dtos.PacienteDto;
import com.devcorp.psiconote.dtos.PsicologoDto;
import com.devcorp.psiconote.services.psicologo.PsicologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/psicologos")
public class PsicologoController {

    private final PsicologoService psicologoService;

    @Autowired
    public PsicologoController(PsicologoService psicologoService) {
        this.psicologoService = psicologoService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<PsicologoDto> actualizarPsicologo(@RequestBody PsicologoDto psicologoDto) {
        PsicologoDto actualizado = psicologoService.actualizarPsicologo(psicologoDto);
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping("/{id}/pacientes")
    public ResponseEntity<List<PacienteDto>> buscarTodosLosPacientes(@PathVariable Long id) {
        List<PacienteDto> pacientes = psicologoService.buscarTodosLosPacientes(id);
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping("/{id}/informes")
    public ResponseEntity<InformeDto> generarInformePaciente(@PathVariable Long id, @RequestBody InformeDto informeDto) {
        InformeDto informeGenerado = psicologoService.generarInformePaciente(id, informeDto);
        return ResponseEntity.ok(informeGenerado);
    }
}
