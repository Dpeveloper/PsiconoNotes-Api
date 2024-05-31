package com.devcorp.psiconote.controller;

import com.devcorp.psiconote.dtos.InformeDto;
import com.devcorp.psiconote.services.informe.InformeService;
import com.devcorp.psiconote.services.informe.InformeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/informes")
public class InformeController {

    private final InformeServiceImp informeService;

    @Autowired
    public InformeController(InformeServiceImp informeService) {
        this.informeService = informeService;
    }

    @PostMapping("/{pacienteId}")
    public ResponseEntity<InformeDto> registrarInforme(@PathVariable Long pacienteId, @RequestBody InformeDto informeDto) {
        InformeDto nuevoInforme = informeService.registrarInforme(pacienteId, informeDto);
        return ResponseEntity.ok(nuevoInforme);
    }

    @PutMapping("/{pacienteId}/{informeId}")
    public ResponseEntity<InformeDto> actualizarInforme(@PathVariable Long pacienteId, @PathVariable Long informeId, @RequestBody InformeDto informeDto) {
        informeDto = new InformeDto(informeId, informeDto.resumen(), informeDto.objetivos(), informeDto.trabajoRealizado(), informeDto.observaciones(), informeDto.respuestasEstudiante(), informeDto.conclusiones(), informeDto.planAccion(), informeDto.notasAdicionales());
        InformeDto informeActualizado = informeService.actualizarInforme(pacienteId, informeDto);
        return ResponseEntity.ok(informeActualizado);
    }

    @GetMapping("/{pacienteId}/{informeId}")
    public ResponseEntity<InformeDto> obtenerInforme(@PathVariable Long pacienteId, @PathVariable Long informeId) {
        InformeDto informe = informeService.obtenerInforme(pacienteId, informeId);
        return ResponseEntity.ok(informe);
    }

    @GetMapping("/{pacienteId}")
    public ResponseEntity<List<InformeDto>> buscarTodosLosInformes(@PathVariable Long pacienteId) {
        List<InformeDto> informes = informeService.buscarTodosLosInformes(pacienteId);
        return ResponseEntity.ok(informes);
    }
}
