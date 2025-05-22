package com.renan.ufem.domain;

import com.renan.ufem.enums.TurnoType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "curso")
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_curso;
    private String nome;
    private int duracao;
    private TurnoType turno;
    private String id_secretaria;

}
