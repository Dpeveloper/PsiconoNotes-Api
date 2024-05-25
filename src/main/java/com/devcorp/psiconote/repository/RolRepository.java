package com.devcorp.psiconote.repository;

import com.devcorp.psiconote.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    public Rol save(Rol rol);
    public Optional<Rol> findById(Long id);
    public Rol findByName(String name);
    public List<Rol> findAll();
    public void delete(Rol rol);
}
