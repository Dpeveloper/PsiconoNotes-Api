package com.devcorp.psiconote.controller;

import com.devcorp.psiconote.dtos.InformeDto;
import com.devcorp.psiconote.dtos.PacienteDto;
import com.devcorp.psiconote.dtos.PsicologoDto;
import com.devcorp.psiconote.services.psicologo.PsicologoService;
import com.devcorp.psiconote.services.psicologo.PsicologoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/psicologos")
public class PsicologoController {

    private final PsicologoServiceImp psicologoService;

    @Autowired
    public PsicologoController(PsicologoServiceImp psicologoService) {
        this.psicologoService = psicologoService;
    }
    @PostMapping
    public ResponseEntity<PsicologoDto> guardarPsicologo(@RequestBody PsicologoDto psicologoDto) {
        PsicologoDto guardado = psicologoService.crearPsicologo(psicologoDto);
        return ResponseEntity.ok(guardado);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PsicologoDto> actualizarPsicologo(@RequestBody PsicologoDto psicologoDto) {
        PsicologoDto actualizado = psicologoService.actualizarPsicologo(psicologoDto);
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping
    public ResponseEntity<List<PsicologoDto>> listarPsicologos() {
        List<PsicologoDto> psicologoDtos = psicologoService.buscarTodosLosPsicologos();
        return ResponseEntity.ok(psicologoDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PacienteDto>> buscarTodosLosPacientes(@PathVariable Long id) {
        List<PacienteDto> pacientes = psicologoService.buscarTodosLosPacientes(id);
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{nombrePsicologo}")
    public ResponseEntity<List<PsicologoDto>> buscarPorNombre(@PathVariable String nombrePsicologo) {
        List<PsicologoDto> psicologoEncontrado = psicologoService.buscarPsicologoPorNombre(nombrePsicologo);
        return ResponseEntity.ok(psicologoEncontrado);
    }

    @PostMapping("/{id}/informes")
    public ResponseEntity<InformeDto> generarInformePaciente(@PathVariable Long id, @RequestBody InformeDto informeDto) {
        InformeDto informeGenerado = psicologoService.generarInformePaciente(id, informeDto);
        return ResponseEntity.ok(informeGenerado);
    }

}
