package com.devcorp.psiconote.repository;

import com.devcorp.psiconote.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario save(Usuario usuario);
    public Optional<Usuario> findById(Long id);
    public List<Usuario> findAll();
    public void delete(Usuario usuario);
    public Optional<Usuario> findByUsername(String username);
}
