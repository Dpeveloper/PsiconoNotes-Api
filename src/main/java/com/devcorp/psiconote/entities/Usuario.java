package com.devcorp.psiconote.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String password;

    @OneToOne(mappedBy = "usuario")
    private Psicologo psicologo;

    @OneToOne(mappedBy = "usuario")
    private Administrador administrador;

    @OneToOne(mappedBy = "usuario")
    private Paciente paciente;

    @ManyToMany(targetEntity = Rol.class,cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "id_usuario",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_rol",referencedColumnName = "id"))
    private Set<Rol> roles;
}
