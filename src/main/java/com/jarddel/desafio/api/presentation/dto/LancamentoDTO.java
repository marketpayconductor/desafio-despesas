package com.jarddel.desafio.api.presentation.dto;

import java.math.BigDecimal;

final public class LancamentoDTO {

    private BigDecimal valor;
    private Long idContaOrigem;
    private Long idContaDestino;

    public BigDecimal getValor() {
        return valor;
    }

    public Long getIdContaOrigem() {
        return idContaOrigem;
    }

    public Long getIdContaDestino() {
        return idContaDestino;
    }

}
