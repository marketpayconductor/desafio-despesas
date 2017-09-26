package br.com.conductor.despesasapp.api.dto.response;

import java.io.Serializable;

public class TransferenciaResponseDTO implements Serializable {

	private static final long serialVersionUID = 2184177684686880003L;
	
	private RetornoDTO retornoOperacao;

	public RetornoDTO getRetornoOperacao() {
		return retornoOperacao;
	}

	public void setRetornoOperacao(RetornoDTO retornoOperacao) {
		this.retornoOperacao = retornoOperacao;
	}
	
}
