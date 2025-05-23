package com.renan.ufem.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import java.time.LocalDate;

@MappedSuperclass
@Data
public abstract class Pessoa {
    private String nome;
    private String CPF;
    private String email;
    private String senha;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String UF;
    private String telefone;
    private String Sexo;
    private LocalDate data_nasc;
}
