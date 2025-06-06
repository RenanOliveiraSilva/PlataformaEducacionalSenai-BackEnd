package com.renan.ufem.services;

import com.renan.ufem.domain.Curso;
import com.renan.ufem.dto.curso.CursoDTO;
import com.renan.ufem.dto.curso.CursoEditarDTO;
import com.renan.ufem.dto.curso.CursoResponseDTO;

import java.util.List;

public interface CursoService {
    Curso criarCurso(String id_secretaria, CursoDTO body);
    Curso editarCurso(String id_curso, CursoEditarDTO body);
    CursoDTO alterarSituacaoCurso(String id_curso);
    CursoDTO buscarCurso(String id_curso);
    List<CursoResponseDTO> buscarCursoPorSecretaria(String id_secretaria);
}
