package com.devcorp.psiconote.controller;

import com.devcorp.psiconote.dtos.*;
import com.devcorp.psiconote.services.psicologo.PsicologoService;

import com.devcorp.psiconote.services.psicologo.PsicologoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/v1/psicologos")
public class PsicologoController {

    private final PsicologoService psicologoService;

    @Autowired
    public PsicologoController(PsicologoServiceImp psicologoService) {
        this.psicologoService = psicologoService;
    }
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPsicologo(@RequestBody PsicologoToSaveDto psicologo){
        PsicologoDto psicologoDto=psicologoService.crearPsicologo(psicologo);
        return ResponseEntity.ok().body(psicologoDto);
    }

    @GetMapping("obtenerPsicologoPorId/{id}")
    public ResponseEntity<?> obtenerPsicologoPorId(@PathVariable Long id){
        PsicologoDto psicologoDto=psicologoService.obtenerPsicologoPorId(id);
        return ResponseEntity.ok().body(psicologoDto);
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<PsicologoDto> actualizarPsicologo(@RequestBody PsicologoDto psicologoDto) {
        PsicologoDto actualizado = psicologoService.actualizarPsicologo(psicologoDto);
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping
    public ResponseEntity<List<PsicologoDto>> listarPsicologos() {
        List<PsicologoDto> psicologoDtos = psicologoService.buscarTodosLosPsicologos();
        return ResponseEntity.ok(psicologoDtos);
    }

    @GetMapping("buscarPacientes/{id}")
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
    public ResponseEntity<InformeDto> generarInformePaciente(@PathVariable Long id, @RequestBody InformeToSaveDto informeToSaveDto) {
        InformeDto informeGenerado = psicologoService.generarInformePaciente(id, informeToSaveDto);
        return ResponseEntity.ok(informeGenerado);
    }

}
