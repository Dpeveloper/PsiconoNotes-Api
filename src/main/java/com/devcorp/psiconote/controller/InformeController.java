package com.devcorp.psiconote.controller;

import com.devcorp.psiconote.dtos.InformeDto;
import com.devcorp.psiconote.dtos.InformeToSaveDto;
import com.devcorp.psiconote.services.informe.InformeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "")
@RestController
@RequestMapping("/psicoNote/v1/informe")
public class InformeController {
    private final InformeService informeService;

    public InformeController(InformeService informeService) {
        this.informeService = informeService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarInforme(@RequestBody InformeToSaveDto informe){
        InformeDto informeDto=informeService.guardadInforme(informe);
        return ResponseEntity.ok().body(informeDto);
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarInforme(@RequestBody InformeToSaveDto informe, @PathVariable Long id){
        InformeDto informeDto=informeService.actualizarInforme(id,informe);
        return ResponseEntity.ok().body(informeDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerInformePorId(@PathVariable Long id){
        InformeDto informeDto=informeService.obtenerInformePorId(id);
        return ResponseEntity.ok().body(informeDto);
    }
    @GetMapping
    public ResponseEntity<?> obtenerTodosInformes(){
        List<InformeDto> informeDtos=informeService.obtenerTodosInformes();
        return ResponseEntity.ok().body(informeDtos);
    }
    @GetMapping("/buscarPorPaciente/{idPaciente}")
    public ResponseEntity<?> obtenerInformePorPaciente(@PathVariable Long idPaciente){
        List<InformeDto> informeDtos=informeService.obtenerInformesPorPaciente(idPaciente);
        return ResponseEntity.ok().body(informeDtos);
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarInformePorId(@PathVariable Long id){
        informeService.eliminarInforme(id);
        return ResponseEntity.noContent().build();
    }
}
