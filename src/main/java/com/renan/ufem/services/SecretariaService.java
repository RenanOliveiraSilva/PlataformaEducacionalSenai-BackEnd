package com.renan.ufem.services;

import com.renan.ufem.domain.Secretaria;
import com.renan.ufem.dto.secretaria.SecretariaLoginRequestDTO;
import com.renan.ufem.dto.secretaria.SecretariaDTO;
import com.renan.ufem.dto.secretaria.SecretariaUpdateDTO;

public interface SecretariaService {
    SecretariaDTO getSecretaria(String id_secretaria);
    Secretaria loginSecretaria(SecretariaLoginRequestDTO login);
    Secretaria criarSecretaria(SecretariaDTO secretaria);
    Secretaria atualizarSecretaria(SecretariaUpdateDTO secretaria, String id_secretaria);
    //Secretaria inativarSecretaria(SecretariaDTO id_secretaria, SecretariaDTO senha);

}
