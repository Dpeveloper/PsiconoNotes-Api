package com.devcorp.psiconote.repository;

import com.devcorp.psiconote.entities.Paciente;
import com.devcorp.psiconote.entities.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    List<Paciente> findByNombre(String nombre);
    List<Paciente> findByEstado(String estado);
}
