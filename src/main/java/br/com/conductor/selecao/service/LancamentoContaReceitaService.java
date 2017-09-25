package br.com.conductor.selecao.service;

import java.util.List;

import br.com.conductor.selecao.model.LancamentoContaReceita;

public interface LancamentoContaReceitaService {
	
	LancamentoContaReceita pesquisarLancamentoContaReceitaPorId(Long id);

	void salvarLancamentoContaReceita(LancamentoContaReceita lancamentoContaReceita);

	void alterarLancamentoContaReceita(LancamentoContaReceita lancamentoContaReceita);

	List<LancamentoContaReceita> listarLancamentoContaReceitas();

}
