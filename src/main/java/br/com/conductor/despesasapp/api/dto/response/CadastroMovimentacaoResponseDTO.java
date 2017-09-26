package br.com.conductor.despesasapp.api.dto.response;

import java.io.Serializable;

public class CadastroMovimentacaoResponseDTO implements Serializable {

	private static final long serialVersionUID = 1528431537274210179L;

	private RetornoDTO retornoOperacao;

	public RetornoDTO getRetornoOperacao() {
		return retornoOperacao;
	}

	public void setRetornoOperacao(RetornoDTO retornoOperacao) {
		this.retornoOperacao = retornoOperacao;
	}
	
}

