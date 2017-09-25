package br.com.conductor.selecao.service;

import java.util.List;

import br.com.conductor.selecao.model.LancamentoContaMovimentacao;

public interface LancamentoContaMovimentacaoService {
	
	LancamentoContaMovimentacao pesquisarLancamentoContaMovimentacaoPorId(Long id);

	void salvarLancamentoContaMovimentacao(LancamentoContaMovimentacao lancamentoContaMovimentacao);

	void alterarLancamentoContaMovimentacao(LancamentoContaMovimentacao lancamentoContaMovimentacao);

	List<LancamentoContaMovimentacao> listarLancamentoContaMovimentacaos();

}
