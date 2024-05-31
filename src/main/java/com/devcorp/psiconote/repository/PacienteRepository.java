package com.devcorp.psiconote.repository;

import com.devcorp.psiconote.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    findByPacienteId
}
