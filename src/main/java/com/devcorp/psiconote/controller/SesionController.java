package com.devcorp.psiconote.controller;

import com.devcorp.psiconote.dtos.SesionDto;
import com.devcorp.psiconote.services.sesiones.SesionService;
import com.devcorp.psiconote.services.sesiones.SesionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sesiones")
public class SesionController {

    private final SesionServiceImp sesionService;

    @Autowired
    public SesionController(SesionServiceImp sesionServiceImp) {
        this.sesionService = sesionServiceImp;
    }

    @PostMapping
    public ResponseEntity<SesionDto> agregarSesion(@RequestBody SesionDto sesionDto,Long pacienteId) {
        SesionDto nuevaSesion = sesionService.agregarSesion(sesionDto, pacienteId);
        return ResponseEntity.ok(nuevaSesion);
    }

    @GetMapping
    public ResponseEntity<List<SesionDto>> obtenerTodasLasSesiones() {
        List<SesionDto> sesiones = sesionService.obtenerTodasLasSesiones();
        return ResponseEntity.ok(sesiones);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<SesionDto>> obtenerTodasLasSesionesPorUsuario(@PathVariable Long usuarioId) {
        List<SesionDto> sesiones = sesionService.obtenerProximasSesionesPorUsuario(usuarioId);
        return ResponseEntity.ok(sesiones);
    }
    @GetMapping("/psicologo/{usuarioId}")
    public ResponseEntity<List<SesionDto>> obtenerTodasLasSesionesPorPsicologo(@PathVariable Long usuarioId) {
        List<SesionDto> sesiones = sesionService.obtenerProximasSesionesPorPsicologo(usuarioId);
        return ResponseEntity.ok(sesiones);
    }

    @PutMapping("/{sesionId}/cancelar")
    public ResponseEntity<List<SesionDto>> cancelarSesion(@PathVariable Long sesionId) {
        List<SesionDto> sesiones = sesionService.cancelarSesion(sesionId);
        return ResponseEntity.ok(sesiones);
    }
}
