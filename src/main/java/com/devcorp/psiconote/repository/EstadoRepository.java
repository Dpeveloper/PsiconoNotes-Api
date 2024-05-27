package com.devcorp.psiconote.repository;

import com.devcorp.psiconote.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado,Long> {
    Optional<Estado> findByNombreEstado(String nombreEstado);
}
