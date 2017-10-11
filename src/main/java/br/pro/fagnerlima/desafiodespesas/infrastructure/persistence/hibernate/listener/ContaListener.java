package br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.listener;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.pro.fagnerlima.desafiodespesas.domain.model.conta.entity.Conta;

public class ContaListener {

    @PrePersist
    public void contaPrePersist(Conta conta) {
        conta.setSaldo(BigDecimal.ZERO);
        conta.setDataAtualizacao(LocalDate.now());
        conta.setAtivo(true);
    }

    @PreUpdate
    public void contaPreUpdate(Conta conta) {
        conta.setDataAtualizacao(LocalDate.now());
    }
}
