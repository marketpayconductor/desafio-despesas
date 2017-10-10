package com.jarddel.desafio.api.domain.model.lancamento;

import java.math.BigDecimal;

import javax.persistence.Entity;

import com.jarddel.desafio.api.domain.model.conta.Conta;

@Entity
public class Receita extends Lancamento {

    public Receita() {
        super();
    }

    public Receita(BigDecimal valor, Conta contaDestino) {
        super(valor, contaDestino);
    }

}
