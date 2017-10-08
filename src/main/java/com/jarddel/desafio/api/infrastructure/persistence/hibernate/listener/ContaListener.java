package com.jarddel.desafio.api.infrastructure.persistence.hibernate.listener;

import javax.persistence.PrePersist;

import com.jarddel.desafio.api.domain.model.conta.Conta;

public class ContaListener {

    @PrePersist
    public void contaPrePersit(Conta conta) {
        conta.zerarSaldo();
        conta.defineDataDefault();
    }

}
