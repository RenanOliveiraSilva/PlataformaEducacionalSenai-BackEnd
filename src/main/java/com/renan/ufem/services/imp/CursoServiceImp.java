package com.renan.ufem.services.imp;

import com.renan.ufem.domain.Curso;
import com.renan.ufem.domain.Secretaria;
import com.renan.ufem.dto.curso.CursoDTO;
import com.renan.ufem.dto.curso.CursoEditarDTO;
import com.renan.ufem.enums.SituacaoType;
import com.renan.ufem.exceptions.ConflictException;
import com.renan.ufem.exceptions.NotFoundException;
import com.renan.ufem.repositories.CursoRepository;
import com.renan.ufem.repositories.SecretariaRepository;
import com.renan.ufem.services.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoServiceImp implements CursoService {

    private final CursoRepository repository;
    private final SecretariaRepository secretariaRepositoryepository;

    @Override
    public Curso criarCurso(String id_secretaria, CursoDTO body) {
        Optional<Curso> cursoExiste = this.repository.findByNome(body.nome());

        Secretaria secretaria = secretariaRepositoryepository.findById(id_secretaria)
                .orElseThrow(() -> new NotFoundException("Secretaria não encontrada."));

        if(cursoExiste.isPresent()) {
            throw new RuntimeException("Curso já cadastrado");
        }

        Curso newCurso = new Curso();
        newCurso.setNome(body.nome());
        newCurso.setDuracao(body.duracao());
        newCurso.setTurno(body.turno());
        newCurso.setDuracao(body.duracao());
        newCurso.setSituacao(SituacaoType.ATIVO);
        newCurso.setIdSecretaria(id_secretaria);

        return this.repository.save(newCurso);
    }

    @Override
    public Curso editarCurso(String id_curso, CursoEditarDTO body){
        Curso curso = this.repository.findById(id_curso)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado."));

        if(body.nome() != null && !body.nome().equals(curso.getNome())){
            this.repository.findByNome(body.nome()).ifPresent(e -> {
                throw new ConflictException("Nome já está em uso.");
            });
            curso.setNome(body.nome());
        };

        if (body.duracao() != null) curso.setDuracao(body.duracao());
        if (body.turno() != null) curso.setTurno(body.turno());

        return this.repository.save(curso);

    }

    @Override
    public CursoDTO alterarSituacaoCurso(String id_curso){
        Curso curso = this.repository.findById(id_curso)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado."));

        switch (curso.getSituacao()) {
            case ATIVO -> curso.setSituacao(SituacaoType.INATIVO);
            case INATIVO -> curso.setSituacao(SituacaoType.ATIVO);
        }
        curso.setData_alteracao(LocalDate.now());

        Curso atualizado = this.repository.save(curso);

        return new CursoDTO(atualizado);
    }

    @Override
    public CursoDTO buscarCurso(String id_curso) {
        Curso curso = this.repository.findById(id_curso)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        return new CursoDTO(curso);
    }

    @Override
    public List<CursoDTO> buscarCursoPorSecretaria(String id_secretaria) {
        List<Curso> cursos = this.repository.findAllByIdSecretaria(id_secretaria);

        if (cursos.isEmpty()) {
            throw new NotFoundException("Nenhum curso encontrado para essa secretaria.");
        }

        return cursos.stream()
                .map(CursoDTO::new)
                .toList();
    }
}
