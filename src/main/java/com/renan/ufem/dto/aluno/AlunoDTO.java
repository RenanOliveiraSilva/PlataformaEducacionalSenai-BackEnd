package com.renan.ufem.dto.aluno;

import com.renan.ufem.domain.Aluno;
import com.renan.ufem.enums.SituacaoType;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;

public record AlunoDTO(
        String nome,
        String CPF,
        String logradouro,
        String bairro,
        Integer numero,
        String cidade,
        String UF,
        @Email(message = "Email deve estar em um formato válido")
        String email,
        String senha,
        String telefone,
        String matricula,
        String sexo,
        LocalDate data_nasc,
        SituacaoType situacao
) {
        public AlunoDTO(Aluno aluno) {
                this(
                        aluno.getNome(),
                        aluno.getCPF(),
                        aluno.getLogradouro(),
                        aluno.getBairro(),
                        aluno.getNumero(),
                        aluno.getCidade(),
                        aluno.getUF(),
                        aluno.getEmail(),
                        null,
                        aluno.getTelefone(),
                        aluno.getMatricula(),
                        aluno.getSexo(),
                        aluno.getData_nasc(),
                        aluno.getSituacao()
                );
        }
}
