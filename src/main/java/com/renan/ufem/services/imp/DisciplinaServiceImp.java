package com.renan.ufem.services.imp;

import com.renan.ufem.domain.Disciplina;
import com.renan.ufem.dto.disciplina.DisciplinaDTO;
import com.renan.ufem.dto.disciplina.DisciplinaResponseDTO;
import com.renan.ufem.enums.SituacaoType;
import com.renan.ufem.exceptions.ConflictException;
import com.renan.ufem.exceptions.NotFoundException;
import com.renan.ufem.repositories.DisciplinaRepository;
import com.renan.ufem.services.DisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisciplinaServiceImp implements DisciplinaService {

    private final DisciplinaRepository repository;

    @Override
    public DisciplinaResponseDTO criar(String idSecretaria, DisciplinaDTO body) {
        if (repository.findByNomeAndIdSecretaria(body.nome(), idSecretaria).isPresent()) {
            throw new ConflictException("Já existe uma disciplina com esse nome para essa secretaria.");
        }

        Disciplina d = new Disciplina();
        d.setNome(body.nome());
        d.setEmenta(body.ementa());
        d.setCargaHoraria(body.cargaHoraria());
        d.setIdSecretaria(idSecretaria);
        d.setSituacao(SituacaoType.ATIVO);

        return new DisciplinaResponseDTO(repository.save(d));
    }

    @Override
    public DisciplinaResponseDTO editar(String idDisciplina, DisciplinaDTO body) {
        Disciplina d = repository.findById(idDisciplina)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada."));

        d.setNome(body.nome());
        d.setEmenta(body.ementa());
        d.setCargaHoraria(body.cargaHoraria());

        return new DisciplinaResponseDTO(repository.save(d));
    }

    @Override
    public DisciplinaResponseDTO buscarPorId(String idDisciplina) {
        Disciplina d = repository.findById(idDisciplina)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada."));
        return new DisciplinaResponseDTO(d);
    }

    @Override
    public List<DisciplinaResponseDTO> buscarPorSecretaria(String idSecretaria) {
        return repository.findAllByIdSecretaria(idSecretaria)
                .stream().map(DisciplinaResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public DisciplinaResponseDTO alterarSituacao(String idDisciplina) {
        Disciplina d = repository.findById(idDisciplina)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada."));

        d.setSituacao(d.getSituacao() == SituacaoType.ATIVO ? SituacaoType.INATIVO : SituacaoType.ATIVO);
        d.setData_alteracao(LocalDate.now());
        return new DisciplinaResponseDTO(repository.save(d));
    }
}