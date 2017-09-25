package br.com.conductor.selecao.service;

import java.util.List;

import br.com.conductor.selecao.model.LancamentoContaDespesa;

public interface LancamentoContaDespesaService {
	
	LancamentoContaDespesa pesquisarLancamentoContaDespesaPorId(Long id);

	void salvarLancamentoContaDespesa(LancamentoContaDespesa lancamentoContaDespesa);

	void alterarLancamentoContaDespesa(LancamentoContaDespesa lancamentoContaDespesa);

	List<LancamentoContaDespesa> listarLancamentoContaDespesas();

}
