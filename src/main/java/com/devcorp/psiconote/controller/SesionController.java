package com.devcorp.psiconote.controller;

import com.devcorp.psiconote.dtos.SesionDto;
import com.devcorp.psiconote.dtos.SesionToSaveDto;
import com.devcorp.psiconote.services.sesion.SesionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
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
    @GetMapping("/obtenerPorFecha/{fechaYHora}")
    public ResponseEntity<?> obtenerSesionPorFecha(@PathVariable String fechaYHora){
        List<SesionDto> sesionDtos=sesionService.obtenerSesionPorFecha(fechaYHora);
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
    @PatchMapping("/actualizarEstado/{nombreEstado}/{id}")
    public ResponseEntity<?> actualizarEstadoSesion(@PathVariable String nombreEstado, @PathVariable Long id){
        SesionDto sesionDto=sesionService.actualizarEstadoSesion(nombreEstado,id);
        return ResponseEntity.ok().body(sesionDto);
    }

    @DeleteMapping("/eliminarSesion/{id}")
    public ResponseEntity<?> eliminarSesion(@PathVariable Long id, @RequestParam String motivo){
        sesionService.eliminarSesion(id, motivo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<?> cancelarSesion(@PathVariable Long id, @PathVariable String notificacion){
        return ResponseEntity.ok().body(sesionService.cancelarSesion(id, notificacion));
    }

    @PutMapping("/reagendar/{id}")
    public ResponseEntity<?> reagendarSesion(@PathVariable Long id, LocalDate fecha, String lugarSesion){
        return ResponseEntity.ok().body(sesionService.reagendarSesion(id,fecha,lugarSesion));
    }

    @PutMapping("/solicitarCancelacion/{id}")
    public ResponseEntity<?> solicitarCancelarSesion(@PathVariable Long id, @PathVariable String notificacion){
        return ResponseEntity.ok().body(sesionService.solicitarCancelarSesion(id, notificacion));
    }

    @PutMapping("/solicitarReagendacion/{id}")
    public ResponseEntity<?> solicitarReagendarSesion(@PathVariable Long id, LocalDateTime fecha, String lugarSesion){
        return ResponseEntity.ok().body(sesionService.solicitarReagendarSesion(id,lugarSesion));
    }
}
