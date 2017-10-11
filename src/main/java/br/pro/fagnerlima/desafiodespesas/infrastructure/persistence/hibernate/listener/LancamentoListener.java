package br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.listener;

import java.time.LocalDate;

import javax.persistence.PrePersist;

import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.entity.Lancamento;

public class LancamentoListener {

    @PrePersist
    public void lancamentoPrePersist(Lancamento lancamento) {
        lancamento.setDataCriacao(LocalDate.now());
    }
}
