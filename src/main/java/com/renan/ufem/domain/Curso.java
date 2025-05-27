package com.renan.ufem.domain;

import com.renan.ufem.enums.SituacaoType;
import com.renan.ufem.enums.TurnoType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "curso")
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_curso")
    private String idCurso;
    private String nome;
    private Integer duracao;
    private LocalDate data_alteracao;
    @Column(name = "id_secretaria")
    private String idSecretaria;

    @Enumerated(EnumType.STRING)
    private TurnoType turno;

    @Enumerated(EnumType.STRING)
    private SituacaoType situacao;

}
