package com.devcorp.psiconote.repository;

import com.devcorp.psiconote.entities.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SesionRepository extends JpaRepository<Sesion,Long> {
    @Query("SELECT s FROM Sesion s WHERE estado.nombreEstado=?1")
    List<Sesion> findByEstado(String estado);

    Optional<Sesion> findByFecha(LocalDate fechaYHora);

    @Query("UPDATE Sesion s SET s.estado.nombreEstado =?1 WHERE s.id=?2")
    Sesion updateEstadoSesion(String nombreEstado,Long id);

    @Query("SELECT s FROM Sesion s WHERE psicologo.id=?1")

    List<Sesion> findByPsicologo(Long idPsicologo);
    List<Sesion> findByPaciente(Long idPaciente);
    List<Sesion> findSesionByPacienteId(Long idPaciente);
}
