package com.devcorp.psiconote.controller;

import com.devcorp.psiconote.dtos.InformeDto;
<<<<<<< HEAD
import com.devcorp.psiconote.dtos.InformeToSaveDto;
import com.devcorp.psiconote.services.informe.InformeService;
=======
import com.devcorp.psiconote.services.informe.InformeService;
import com.devcorp.psiconote.services.informe.InformeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> c2b5cd6c6495d6199b7e5e5780e3602de286ce03
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
<<<<<<< HEAD
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
=======
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
>>>>>>> c2b5cd6c6495d6199b7e5e5780e3602de286ce03
    }
}
