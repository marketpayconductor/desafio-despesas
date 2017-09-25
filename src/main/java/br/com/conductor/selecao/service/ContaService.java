package br.com.conductor.selecao.service;


import java.util.List;

import br.com.conductor.selecao.model.Conta;

public interface ContaService {
	
	Conta pesquisarContaPorId(Long id);

	void salvarConta(Conta conta);

	void alterarConta(Conta conta);

	List<Conta> listarContas();

	boolean verificarContaExiste(Conta conta);
}