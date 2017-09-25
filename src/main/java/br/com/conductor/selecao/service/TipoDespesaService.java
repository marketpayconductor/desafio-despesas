package br.com.conductor.selecao.service;

import java.util.List;

import br.com.conductor.selecao.model.TipoDespesa;

public interface TipoDespesaService {
	
	TipoDespesa pesquisarTipoDespesaPorId(Long id);

	void salvarTipoDespesa(TipoDespesa tipoDespesa);

	void alterarTipoDespesa(TipoDespesa tipoDespesa);

	List<TipoDespesa> listarTipoDespesas();

	boolean verificarTipoDespesaExiste(TipoDespesa tipoDespesa);
}
