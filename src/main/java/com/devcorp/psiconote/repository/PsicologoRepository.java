package com.devcorp.psiconote.repository;

import com.devcorp.psiconote.entities.Psicologo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PsicologoRepository extends JpaRepository<Psicologo, Long> {
    public Psicologo save(Psicologo psicologo);
    public Optional<Psicologo> findByNombre(String name);
    public Optional<Psicologo> findPsicologoById(Long id);
    public List<Psicologo> findAll();
    public void deleteById(Long id);
}
