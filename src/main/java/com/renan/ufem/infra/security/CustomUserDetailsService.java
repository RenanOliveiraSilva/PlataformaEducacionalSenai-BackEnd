package com.renan.ufem.infra.security;

import com.renan.ufem.infra.security.common.UsuarioAutenticavel;
import com.renan.ufem.repositories.AlunoRepository;
import com.renan.ufem.repositories.ProfessorRepository;
import com.renan.ufem.repositories.SecretariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private final SecretariaRepository secretariaRepository;

    @Autowired
    public CustomUserDetailsService(
            AlunoRepository alunoRepository,
            ProfessorRepository professorRepository,
            SecretariaRepository secretariaRepository
    ) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.secretariaRepository = secretariaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
