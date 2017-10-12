package br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.listener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;

import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.entity.Lancamento;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.listener.exception.ContaInativaException;

public class LancamentoListener {

    @PrePersist
    public void lancamentoPrePersist(Lancamento lancamento) throws ContaInativaException {
        if (!lancamento.getConta().isAtivo()) {
            throw new ContaInativaException();
        }

        lancamento.setDataCriacao(LocalDateTime.now());
    }
}
