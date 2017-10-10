package com.jarddel.desafio.api.infrastructure.persistence.hibernate.listener;

import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.jarddel.desafio.api.domain.exception.UsuarioInativoException;
import com.jarddel.desafio.api.domain.model.conta.Conta;

public class ContaListener {

    @Autowired
    protected MessageSource messageSource;

    @PrePersist
    public void contaPrePersit(Conta conta) {
        conta.zerarSaldo();
        conta.defineDataDefault();
        verificarUsuarioAtivo(conta);
    }

    public void verificarUsuarioAtivo(Conta conta) {
        if (!conta.getUsuario().isAtivo()) {
            throw new UsuarioInativoException();
        }
    }

}
