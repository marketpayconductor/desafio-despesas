package br.pro.fagnerlima.desafiodespesas.domain.service;

import br.pro.fagnerlima.desafiodespesas.domain.model.conta.entity.Conta;

public interface ContaService extends BaseService<Conta, Long> {

    /**
     * Altera o status da propriedade ativo.
     * 
     * @param id
     */
    public void updateAtivo(Long id);
}
