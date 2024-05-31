package com.devcorp.psiconote.repository;

import com.devcorp.psiconote.entities.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SesionRepository extends JpaRepository<Sesion,Long> {

    List<Sesion> findSesionByEstadoNombreEstadoAndPacienteId(String estado, Long id);
    List<Sesion> findSesionByEstadoNombreEstadoAndPsicologoId(String estado, Long id);
    Optional<Sesion> findByFechaYHora(LocalDateTime fechaYHora);
    List<Sesion> findSesionByPsicologoId(Long idPsicologo);

    List<Sesion> findSesionByPacienteId(Long idPaciente);
}
