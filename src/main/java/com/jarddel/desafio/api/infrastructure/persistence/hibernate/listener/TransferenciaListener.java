package com.jarddel.desafio.api.infrastructure.persistence.hibernate.listener;

import javax.persistence.PrePersist;

import com.jarddel.desafio.api.domain.exception.ContaDestinoInativaException;
import com.jarddel.desafio.api.domain.exception.ContaOrigemInativaException;
import com.jarddel.desafio.api.domain.model.conta.Conta;
import com.jarddel.desafio.api.domain.model.lancamento.Transferencia;

public class TransferenciaListener extends LancamentoListener {

    @PrePersist
    public void transferenciaPrePersit(Transferencia transferencia) {
        super.lancamentoPrePersit(transferencia);
    }

    public void verificarContaAtiva(Transferencia transferencia) {
        Conta contaOrigem = transferencia.getContaOrigem();
        Conta contaDestino = transferencia.getContaDestino();

        if (!contaOrigem.isAtivo()) {
            throw new ContaOrigemInativaException();
        }

        if (!contaDestino.isAtivo()) {
            throw new ContaDestinoInativaException();
        }
    }

}
