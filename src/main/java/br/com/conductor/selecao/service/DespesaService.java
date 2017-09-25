package br.com.conductor.selecao.service;

import java.util.List;

import br.com.conductor.selecao.dto.CancelarDespesaDTO;
import br.com.conductor.selecao.dto.PagarDespesaDTO;
import br.com.conductor.selecao.dto.SalvarAlterarDespesaDTO;
import br.com.conductor.selecao.exception.DespesaNaoAbertaException;
import br.com.conductor.selecao.exception.EntidadeNaoEncontrada;
import br.com.conductor.selecao.exception.ParametroRequestInvalidoException;
import br.com.conductor.selecao.model.Despesa;

public interface DespesaService {

	Despesa pesquisarDespesaPorId(Long id);

	void salvarDespesa(Despesa despesa);

	Despesa salvarDespesa(SalvarAlterarDespesaDTO despesaDTO)
			throws EntidadeNaoEncontrada, ParametroRequestInvalidoException;

	void alterarDespesa(Despesa despesa);

	Despesa alterarDespesa(long idDespesa, SalvarAlterarDespesaDTO despesaDTO)
			throws ParametroRequestInvalidoException, DespesaNaoAbertaException, EntidadeNaoEncontrada;

	List<Despesa> listarDespesas();

	boolean verificarDespesaExiste(Despesa despesa);

	Despesa pagarDespesa(Long idDespesa, PagarDespesaDTO DespesaDTO)
			throws EntidadeNaoEncontrada, DespesaNaoAbertaException;

	void cancelarDespesa(Long idDespesa, CancelarDespesaDTO cancelarDespesaDTO)
			throws EntidadeNaoEncontrada, DespesaNaoAbertaException;

}