package com.renan.ufem.services;

import com.renan.ufem.domain.Grade;
import com.renan.ufem.dto.grade.GradeDTO;

import java.util.List;

public interface GradeService {
    Grade criarGrade(GradeDTO dto);
    List<Grade> listarGrades();
}
