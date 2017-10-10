package com.jarddel.desafio.api.domain.model.conta;

public enum Tipo {
    COMUM("Comum"), PREMIUM("Premium");

    private String descricao;

    Tipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
