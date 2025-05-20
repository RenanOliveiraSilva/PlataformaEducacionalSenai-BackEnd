package com.renan.ufem.infra.security.professor;

import com.renan.ufem.domain.aluno.Aluno;
import com.renan.ufem.domain.professor.Professor;
import com.renan.ufem.repositories.AlunoRepository;
import com.renan.ufem.repositories.ProfessorRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class ProfessorSecurityFilter extends OncePerRequestFilter {
    @Autowired
    ProfessorTokenService tokenService;
    @Autowired
    ProfessorRepository professorRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // ✅ Verifica se a rota começa com /aluno
        if (!request.getRequestURI().startsWith("/professor")) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = this.recoverToken(request);
        var login = tokenService.validateToken(token);

        if(login != null){
            Professor professor = professorRepository.findByCPF(login).orElseThrow(() -> new RuntimeException("Professor Not Found"));
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_PROFESSOR"));
            var authentication = new UsernamePasswordAuthenticationToken(professor, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
