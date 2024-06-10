package com.devcorp.psiconote.repository;

import com.devcorp.psiconote.entities.Informe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InformeRepository extends JpaRepository<Informe,Long> {
<<<<<<< HEAD
    @Query("SELECT i FROM Informe i WHERE paciente.id=?1")
=======
    @Query("SELECT s FROM Sesion s WHERE Pacientes.id=?1")
>>>>>>> c2b5cd6c6495d6199b7e5e5780e3602de286ce03
    List<Informe> findByPaciente(Long idPaciente);


}
