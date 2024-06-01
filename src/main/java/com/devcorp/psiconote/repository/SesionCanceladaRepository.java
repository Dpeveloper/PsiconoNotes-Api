package com.devcorp.psiconote.repository;

import com.devcorp.psiconote.entities.Sesion;
import com.devcorp.psiconote.entities.SesionCancelada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SesionCanceladaRepository extends JpaRepository<SesionCancelada,Long> {
    List<SesionCancelada> findByMotivoCancelacion(String motivo);
    //@Query("SELECT s FROM Sesion s WHERE psicologo.id=?1")
    List<Sesion> findByPsicologo(Long idPsicologo);

    //@Query("SELECT s FROM Sesion s WHERE paciente.id=?1")
    List<Sesion> findByPaciente(Long idPaciente);


}
