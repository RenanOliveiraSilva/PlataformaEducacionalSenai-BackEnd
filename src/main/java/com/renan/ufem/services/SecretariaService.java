package com.renan.ufem.services;

import com.renan.ufem.domain.Secretaria;
import com.renan.ufem.dto.secretaria.SecretariaLoginRequestDTO;

public interface SecretariaService {
    Secretaria criarSecretaria(Secretaria secretaria);
    Secretaria loginSecretaria(SecretariaLoginRequestDTO dto);
}
