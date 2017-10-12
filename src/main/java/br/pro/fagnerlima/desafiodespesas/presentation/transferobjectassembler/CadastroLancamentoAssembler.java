package br.pro.fagnerlima.desafiodespesas.presentation.transferobjectassembler;

import br.pro.fagnerlima.desafiodespesas.domain.model.conta.entity.Conta;
import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.entity.Lancamento;
import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.enumeration.TipoLancamento;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.CadastroLancamentoTO;

public class CadastroLancamentoAssembler {

    public Lancamento getEntity(CadastroLancamentoTO cadastroLancamentoTO, Conta conta, TipoLancamento tipo) {
        return new Lancamento(tipo, conta, cadastroLancamentoTO.getValor(),
                cadastroLancamentoTO.getDescricao());
    }
}
