package br.com.conductor.selecao.service;

import java.util.List;

import br.com.conductor.selecao.model.LancamentoConta;

public interface LancamentoContaService {

	LancamentoConta pesquisarLancamentoContaPorId(Long id);

	void salvarLancamentoConta(LancamentoConta lancamentoConta);

	void alterarLancamentoConta(LancamentoConta lancamentoConta);

	List<LancamentoConta> listarLancamentoContas();

}
