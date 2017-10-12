package br.pro.fagnerlima.desafiodespesas.presentation.transferobjectassembler;

import br.pro.fagnerlima.desafiodespesas.domain.model.conta.entity.Conta;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.ContaTO;

public class ContaAssembler {

    public ContaTO getData(Conta conta) {
        return new ContaTO(conta.getId(), conta.getUsuario().getInformacaoPessoal().getNome(), conta.getSaldo(),
                conta.isAtivo());
    }
}
