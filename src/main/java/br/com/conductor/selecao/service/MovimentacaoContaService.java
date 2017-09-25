package br.com.conductor.selecao.service;

import java.util.List;

import br.com.conductor.selecao.dto.SalvarMovimentacaoContaDTO;
import br.com.conductor.selecao.exception.EntidadeNaoEncontrada;
import br.com.conductor.selecao.exception.ParametroRequestInvalidoException;
import br.com.conductor.selecao.model.MovimentacaoConta;

public interface MovimentacaoContaService {

	MovimentacaoConta pesquisarMovimentacaoContaPorId(Long id);

	List<MovimentacaoConta> listarMovimentacaoContas();

	MovimentacaoConta salvarMovimentacaoConta(SalvarMovimentacaoContaDTO salvarMovimentacaoContaDTO)
			throws EntidadeNaoEncontrada, ParametroRequestInvalidoException;

}