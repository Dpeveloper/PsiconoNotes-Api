package com.devcorp.psiconote.controller;

import com.devcorp.psiconote.dtos.AdministradorDto;
import com.devcorp.psiconote.services.administrador.AdministradorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Administrador")
public class AdministradorController {

    private final AdministradorServiceImp administradorServiceImp;

    @Autowired
    public AdministradorController(AdministradorServiceImp administradorServiceImp) {
        this.administradorServiceImp = administradorServiceImp;
    }

    @PutMapping
    public ResponseEntity<AdministradorDto> updateAdministrador(@RequestBody AdministradorDto administradorDto) {
        return ResponseEntity.ok().body(administradorServiceImp.editarAdministrador(administradorDto));
    }
}
