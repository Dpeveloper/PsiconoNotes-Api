package com.devcorp.psiconote.repository;

import com.devcorp.psiconote.entities.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SesionRepository extends JpaRepository<Sesion,Long> {
    List<Sesion> findByEstado(String estado);
    Optional<Sesion> findByFechaYHora(LocalDateTime fechaYHora);
    @Query("SELECT s FROM Sesion s WHERE psicologo.id=?1")
    List<Sesion> findByPsicologo(Long idPsicologo);

    @Query("SELECT s FROM Sesion s WHERE paciente.id=?1")
    List<Sesion> findByPaciente(Long idPaciente);
}
