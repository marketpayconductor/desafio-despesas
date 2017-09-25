package br.com.conductor.despesasapp.api.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;

public class ConsultaSaldoResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 7040972847818958775L;

	private RetornoDTO retornoOperacao;
	
	private BigDecimal saldo;
	
	public ConsultaSaldoResponseDTO() {
	}
	
	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public RetornoDTO getRetornoOperacao() {
		return retornoOperacao;
	}

	public void setRetornoOperacao(RetornoDTO retornoOperacao) {
		this.retornoOperacao = retornoOperacao;
	}

}
