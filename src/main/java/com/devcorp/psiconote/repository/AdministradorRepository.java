package com.devcorp.psiconote.repository;

import com.devcorp.psiconote.entities.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    public Administrador save(Administrador administrador);
    public Optional<Administrador> findById(Long id);
    public List<Administrador> findAll();
    public void delete(Administrador administrador);
}
