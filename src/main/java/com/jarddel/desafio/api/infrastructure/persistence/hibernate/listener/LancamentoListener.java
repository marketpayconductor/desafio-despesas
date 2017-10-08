package com.jarddel.desafio.api.infrastructure.persistence.hibernate.listener;

import javax.persistence.PrePersist;

import com.jarddel.desafio.api.domain.model.lancamento.Lancamento;

public class LancamentoListener {

    @PrePersist
    public void defineDataDefaultPrePersit(Lancamento lancamento) {
        lancamento.defineDataDefault();
    }

}
