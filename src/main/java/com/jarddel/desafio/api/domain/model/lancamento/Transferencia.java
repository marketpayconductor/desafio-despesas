package com.jarddel.desafio.api.domain.model.lancamento;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.jarddel.desafio.api.domain.model.conta.Conta;

@Entity
public class Transferencia extends Lancamento {

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "conta_origem")
    private Conta contaOrigem;

    public Transferencia() {
        super();
    }

    public Transferencia(BigDecimal valor, Conta contaDestino, Conta contaOrigem) {
        super(valor, contaDestino);
        this.contaOrigem = contaOrigem;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }
}
