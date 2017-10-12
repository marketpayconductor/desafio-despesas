package br.pro.fagnerlima.desafiodespesas.presentation.transferobjectassembler;

import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.entity.Lancamento;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.LancamentoTO;

public class LancamentoAssembler {

    public LancamentoTO getData(Lancamento lancamento) {
        return new LancamentoTO(lancamento.getId(), lancamento.getDataCriacao(), lancamento.getTipo(),
                lancamento.getDescricao(), lancamento.getValor());
    }
}
