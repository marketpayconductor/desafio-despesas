package br.com.conductor.selecao.service;

import java.util.List;

import br.com.conductor.selecao.dto.CancelarReceitaDTO;
import br.com.conductor.selecao.dto.PagarReceitaDTO;
import br.com.conductor.selecao.dto.SalvarAlterarReceitaDTO;
import br.com.conductor.selecao.exception.EntidadeNaoEncontrada;
import br.com.conductor.selecao.exception.ParametroRequestInvalidoException;
import br.com.conductor.selecao.exception.ReceitaNaoAbertaException;
import br.com.conductor.selecao.model.Receita;

public interface ReceitaService {

	Receita pesquisarReceitaPorId(Long id);

	void salvarReceita(Receita receita);

	Receita salvarReceita(SalvarAlterarReceitaDTO receitaDTO)
			throws ParametroRequestInvalidoException, EntidadeNaoEncontrada;

	void alterarReceita(Receita receita);

	Receita alterarReceita(long id, SalvarAlterarReceitaDTO receitaDTO)
			throws ParametroRequestInvalidoException, ReceitaNaoAbertaException, EntidadeNaoEncontrada;

	List<Receita> listarReceitas();

	boolean verificarReceitaExiste(Receita receita);

	Receita pagarReceita(Long idReceita, PagarReceitaDTO receitaDTO)
			throws EntidadeNaoEncontrada, ReceitaNaoAbertaException;

	void cancelarReceita(Long idReceita, CancelarReceitaDTO cancelarReceitaDTO)
			throws EntidadeNaoEncontrada, ReceitaNaoAbertaException;

}