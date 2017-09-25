package br.com.conductor.selecao.service;

import java.util.List;

import br.com.conductor.selecao.model.TipoReceita;

public interface TipoReceitaService {
	
	TipoReceita pesquisarTipoReceitaPorId(Long id);

	void salvarTipoReceita(TipoReceita tipoReceita);

	void alterarTipoReceita(TipoReceita tipoReceita);

	List<TipoReceita> listarTipoReceitas();

	boolean verificarTipoReceitaExiste(TipoReceita tipoReceita);
}
