package br.com.conductor.despesasapp.api.dto.response;

import java.io.Serializable;
import java.util.List;

public class HistoricoMovimentacaoResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 1873227760583519867L;

	private RetornoDTO retornoOperacao;
	
	private List<MovimentoResponseDTO> listaDeMovimentacoes;

	public List<MovimentoResponseDTO> getListaDeMovimentacoes() {
		return listaDeMovimentacoes;
	}

	public void setListaDeMovimentacoes(List<MovimentoResponseDTO> listaDeMovimentacoes) {
		this.listaDeMovimentacoes = listaDeMovimentacoes;
	}

	public RetornoDTO getRetornoOperacao() {
		return retornoOperacao;
	}

	public void setRetornoOperacao(RetornoDTO retornoOperacao) {
		this.retornoOperacao = retornoOperacao;
	}
	
}
