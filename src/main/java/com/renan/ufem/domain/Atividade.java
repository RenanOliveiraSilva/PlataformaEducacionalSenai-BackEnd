package com.renan.ufem.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "atividade")
@Data
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_atividade")
    private String idAtividade;

    private String nome;
    private String descricao;

    @Column(name = "data_entrega")
    private LocalDate dataEntrega;

    private Float peso;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_turma")
    private Turma turma;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_professor")
    private Professor professor;
}
