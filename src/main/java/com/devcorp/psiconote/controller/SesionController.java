package com.devcorp.psiconote.controller;

import com.devcorp.psiconote.dtos.SesionDto;
import com.devcorp.psiconote.dtos.SesionToSaveDto;
import com.devcorp.psiconote.services.sesion.SesionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
