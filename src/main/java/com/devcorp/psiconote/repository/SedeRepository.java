package com.devcorp.psiconote.repository;

import com.devcorp.psiconote.entities.Sede;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SedeRepository extends JpaRepository<Sede, Long> {
    public Sede save(Sede institucion);
    public Optional<Sede> findById(Long id);
    public List<Sede> findAll();
    public void deleteById(int id);
}
