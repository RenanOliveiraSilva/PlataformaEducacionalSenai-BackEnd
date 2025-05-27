package com.renan.ufem.dto.professor;

import com.renan.ufem.domain.Professor;
import com.renan.ufem.enums.SituacaoType;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;

public record ProfessorDTO(
        String nome,
        String CPF,
        SituacaoType situacao,
        String logradouro,
        String bairro,
        Integer numero,
        String cidade,
        String UF,
        @Email(message = "Email deve estar em um formato válido")
        String email,
        String senha,
        String telefone,
        String sexo,
        LocalDate data_nasc,
        String id_secretaria
) {
    public ProfessorDTO(Professor professor) {
        this(
                professor.getNome(),
                professor.getCPF(),
                professor.getSituacao(),
                professor.getLogradouro(),
                professor.getBairro(),
                professor.getNumero(),
                professor.getCidade(),
                professor.getUF(),
                professor.getEmail(),
                null,
                professor.getTelefone(),
                professor.getSexo(),
                professor.getData_nasc(),
                professor.getIdSecretaria()

        );
    }
}
