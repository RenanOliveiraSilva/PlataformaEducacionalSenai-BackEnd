// Disciplina.java (Domain)
package com.renan.ufem.domain;

import com.renan.ufem.enums.SituacaoType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "disciplina")
@Data
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_disciplina")
    private String idDisciplina;

    private String nome;
    private String ementa;

    @Column(name = "carga_horaria")
    private Integer cargaHoraria;
    private LocalDate data_alteracao;

    @Column(name = "id_secretaria")
    private String idSecretaria;

    @Enumerated(EnumType.STRING)
    private SituacaoType situacao;
}