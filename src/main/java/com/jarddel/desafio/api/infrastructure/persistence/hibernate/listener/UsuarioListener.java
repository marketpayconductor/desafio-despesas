package com.jarddel.desafio.api.infrastructure.persistence.hibernate.listener;

import javax.persistence.PrePersist;

import com.jarddel.desafio.api.domain.model.usuario.Usuario;
import com.jarddel.desafio.api.infrastructure.security.util.SenhaUtil;

public class UsuarioListener {

    @PrePersist
    public void usuarioPrePersit(Usuario usuario) {
        usuario.setSenha(SenhaUtil.encriptar(usuario.getSenha()));
    }

}
