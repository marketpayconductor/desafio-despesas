package com.jarddel.desafio.api.infrastructure.persistence.hibernate.listener;

import javax.persistence.PrePersist;

import com.jarddel.desafio.api.domain.exception.ContaDestinoInativaException;
import com.jarddel.desafio.api.domain.model.lancamento.Lancamento;

public class LancamentoListener {

    @PrePersist
    public void lancamentoPrePersit(Lancamento lancamento) {
        lancamento.defineDataDefault();
        verificarContaAtiva(lancamento);
    }

    public void verificarContaAtiva(Lancamento lancamento) {
        if (!lancamento.getContaDestino().isAtivo()) {
            throw new ContaDestinoInativaException();
        }
    }

}
