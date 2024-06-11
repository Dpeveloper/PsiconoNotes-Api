package com.devcorp.psiconote.repository;

import com.devcorp.psiconote.entities.Informe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InformeRepository extends JpaRepository<Informe,Long> {
    @Query("SELECT i FROM Informe i WHERE paciente.id=?1")
    List<Informe> findByPaciente(Long idPaciente);
}
