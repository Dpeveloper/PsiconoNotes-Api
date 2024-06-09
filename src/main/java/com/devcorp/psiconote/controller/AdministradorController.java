package com.devcorp.psiconote.controller;

import com.devcorp.psiconote.services.administrador.AdministradorServiceImp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Administrador")
public class AdministradorController {

    private final AdministradorServiceImp administradorServiceImp;

    public AdministradorController(AdministradorServiceImp administradorServiceImp) {
        this.administradorServiceImp = administradorServiceImp;
    }
}
