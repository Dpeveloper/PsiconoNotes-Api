package com.devcorp.psiconote.controller;

import com.devcorp.psiconote.dtos.SesionDto;
<<<<<<< HEAD
import com.devcorp.psiconote.dtos.SesionToSaveDto;
import com.devcorp.psiconote.entities.Sesion;
import com.devcorp.psiconote.services.sesion.SesionService;
=======
import com.devcorp.psiconote.services.sesiones.SesionService;
import com.devcorp.psiconote.services.sesiones.SesionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> c2b5cd6c6495d6199b7e5e5780e3602de286ce03
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
<<<<<<< HEAD
@RequestMapping("/psicoNote/v1/sesion")
public class SesionController {
    private final SesionService sesionService;

    public SesionController(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarSesion(@RequestBody SesionToSaveDto sesion){
        SesionDto sesionDto=sesionService.guardarSesion(sesion);
        return ResponseEntity.ok().body(sesionDto);
    }
    @GetMapping
    public ResponseEntity<?> obtenerSesiones(){
        List<SesionDto> sesionDtos=sesionService.obtenerSesiones();
        return ResponseEntity.ok().body(sesionDtos);
=======
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
>>>>>>> c2b5cd6c6495d6199b7e5e5780e3602de286ce03
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarSesion(@PathVariable Long id,@RequestBody SesionToSaveDto sesion){
        SesionDto sesion1=sesionService.actualizarSesion(id,sesion);
        return ResponseEntity.ok().body(sesion1);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerSesionPorId(@PathVariable Long id){
        SesionDto sesionDto=sesionService.obtenerSesionPorId(id);
        return ResponseEntity.ok().body(sesionDto);
    }
    @GetMapping("/obtenerPorEstado/{nombreEstado}")
    public ResponseEntity<?> obtenerSesionPorEstado(@PathVariable String nombreEstado){
        List<SesionDto> sesionDtos=sesionService.obtenerSesionesPorEstado(nombreEstado);
        return  ResponseEntity.ok().body(sesionDtos);
    }
    @GetMapping("/obtenerPorFechaYHora/{fechaYHora}")
    public ResponseEntity<?> obtenerSesionPorFechaYHora(@PathVariable String fechaYHora){
        List<SesionDto> sesionDtos=sesionService.obtenerSesionPorFechaYHora(fechaYHora);
        return ResponseEntity.ok().body(sesionDtos);
    }
    @GetMapping("/obtenerPorPaciente/{idPaciente}")
    public ResponseEntity<?> obtenerSesionPorPaciente(@PathVariable Long idPaciente){
        List<SesionDto> sesionDtos=sesionService.obtenerSesionesPorPaciente(idPaciente);
        return ResponseEntity.ok().body(sesionDtos);
    }
    @GetMapping("/obtenerPorPsicologo/{idPsicologo}")
    public ResponseEntity<?> obtenerSesionPorPsicologo(@PathVariable Long idPsicologo){
        List<SesionDto> sesionDtos=sesionService.obtenerSesionesPorPsicologo(idPsicologo);
        return ResponseEntity.ok().body(sesionDtos);
    }
    @PatchMapping("/actualizarInforme/{idSesion}/{idInforme}")
    public ResponseEntity<?> actualizarInformeSesion(@PathVariable Long idSesion,@PathVariable Long idInforme){
        SesionDto sesionDto=sesionService.actualizarInformeSesion(idSesion,idInforme);
        return ResponseEntity.ok().body(sesionDto);
    }
    @DeleteMapping("/eliminarSesion/{id}")
    public ResponseEntity<?> eliminarSesion(@PathVariable Long id){
        sesionService.eliminarSesion(id);
        return ResponseEntity.noContent().build();
    }
}
