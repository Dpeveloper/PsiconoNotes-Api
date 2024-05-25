package com.devcorp.psiconote.repository;

import com.devcorp.psiconote.entities.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InstitucionRepository extends JpaRepository<Institucion, Long> {
    public Institucion save(Institucion institucion);
    public Optional<Institucion> findById(Long id);
    public List<Institucion> findAll();
    public void deleteById(int id);
}
