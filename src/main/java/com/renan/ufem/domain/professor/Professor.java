package com.renan.ufem.domain.professor;

import com.renan.ufem.domain.pessoa.Pessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "professor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Professor extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_professor;
    private String id_secretaria;
}
