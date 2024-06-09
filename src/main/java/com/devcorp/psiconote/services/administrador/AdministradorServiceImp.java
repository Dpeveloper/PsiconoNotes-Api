package com.devcorp.psiconote.services.administrador;

import com.devcorp.psiconote.dtos.AdministradorDto;
import com.devcorp.psiconote.dtos.mappers.AdministradorMapper;
import com.devcorp.psiconote.entities.Administrador;
import com.devcorp.psiconote.repository.AdministradorRepository;
import org.springframework.stereotype.Service;

@Service
public class AdministradorServiceImp implements AdministradorService{

    private AdministradorRepository administradorRepository;
    private AdministradorMapper administradorMapper;

    public AdministradorServiceImp(AdministradorRepository administradorRepository, AdministradorMapper administradorMapper) {
        this.administradorRepository = administradorRepository;
        this.administradorMapper = administradorMapper;
    }

    @Override
    public AdministradorDto editarAdministrador(AdministradorDto administradorDto) {
        Administrador administrador = administradorRepository.findById(administradorDto.id())
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        administrador.setNombre(administradorDto.nombre());
        administrador.setApellido(administradorDto.apellido());
        administrador.setEmail(administradorDto.email());
        administrador.setFechaNacimiento(administradorDto.fechaNacimiento());

        return administradorMapper.toAdministradorDto(administradorRepository.save(administrador));

    }
}
