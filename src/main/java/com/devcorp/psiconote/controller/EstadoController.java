package com.devcorp.psiconote.controller;

import com.devcorp.psiconote.dtos.EstadoDto;
import com.devcorp.psiconote.dtos.EstadoToSaveDto;
import com.devcorp.psiconote.services.estado.EstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/psicoNote/v1/estado")
public class EstadoController {
    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }
    @GetMapping()
    public ResponseEntity<?> obtenerEstados(){
        List<EstadoDto> estadoDtos=estadoService.buscarTodosEstados();
        return ResponseEntity.ok().body(estadoDtos);
    }
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarEstado(@RequestBody EstadoToSaveDto estado){
        EstadoDto estadoGuardado=estadoService.guardarEstado(estado);
        return ResponseEntity.ok().body(estadoGuardado);
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarEstado(@PathVariable Long id, @RequestBody EstadoToSaveDto estado){
        EstadoDto estadoActualizado=estadoService.actualizarEstado(id,estado);
        return ResponseEntity.ok().body(estadoActualizado);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try {
            EstadoDto estadoEncontrado=estadoService.buscarEstadoId(id);
            return ResponseEntity.ok().body(estadoEncontrado);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("buscarPorNombre/{nombreEstado}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombreEstado){
        try{
            EstadoDto estadoEncontrado=estadoService.buscarEstadoPorNombre(nombreEstado);
            return ResponseEntity.ok().body(estadoEncontrado);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarEstado(@PathVariable Long id){
        estadoService.eliminarEstado(id);
        return ResponseEntity.noContent().build();
    }

}
