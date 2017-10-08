package com.jarddel.desafio.api.domain.model.usuario;

public enum Papel {
    ROLE_COMUM("ROLE_COMUM"), ROLE_ADMIN("ROLE_ADMIN");

    private String descricao;

    Papel(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
