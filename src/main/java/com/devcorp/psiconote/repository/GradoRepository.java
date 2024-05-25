package com.devcorp.psiconote.repository;

import com.devcorp.psiconote.entities.Grado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GradoRepository extends JpaRepository<Grado, Long> {
    public Grado save(Grado grado);
    public Optional<Grado> findById(Long id);
    public List<Grado> findAll();
    public void deleteById(int id);
}
