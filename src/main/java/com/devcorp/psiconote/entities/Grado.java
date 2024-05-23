package com.devcorp.psiconote.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "Grados")
public class Grado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "id_institucion",referencedColumnName = "id")
    private Institucion institucion;
}
