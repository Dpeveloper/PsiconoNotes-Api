package com.devcorp.psiconote.controller;

import com.devcorp.psiconote.dtos.EstadoDto;
import com.devcorp.psiconote.dtos.PacienteDto;
import com.devcorp.psiconote.dtos.PacienteToSaveDto;
import com.devcorp.psiconote.services.paciente.PacienteService;
import com.devcorp.psiconote.services.paciente.PacienteServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    private final PacienteServiceImp pacienteService;

    @Autowired
    public PacienteController(PacienteServiceImp pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<PacienteDto> guardarPaciente(@RequestBody PacienteToSaveDto pacienteDto) {
        PacienteDto nuevoPaciente = pacienteService.guardarPaciente(pacienteDto);
        return ResponseEntity.ok(nuevoPaciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> actualizarPaciente(@RequestBody PacienteDto pacienteDto) {
        PacienteDto pacienteActualizado = pacienteService.actualizarPaciente(pacienteDto);
        return ResponseEntity.ok(pacienteActualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPacientePorId(@PathVariable Long id) {
        PacienteDto paciente = pacienteService.buscarPacientePorId(id);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDto>> buscarPacientes() {
        List<PacienteDto> pacientes = pacienteService.buscarPacientes();
        return ResponseEntity.ok(pacientes);
    }

    /*@PutMapping("/{id}/estado")
    public ResponseEntity<PacienteDto> actualizarEstado(@PathVariable Long id, @RequestBody EstadoDto estadoDto) {
        PacienteDto pacienteActualizado = pacienteService.actualizarEstado(id, estadoDto);
        return ResponseEntity.ok(pacienteActualizado);
    }
     */
}
