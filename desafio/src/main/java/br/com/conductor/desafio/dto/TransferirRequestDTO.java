package br.com.conductor.desafio.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferirRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("cpfOrigem")
	private String cpfOrigem;

	@JsonProperty("cpfDestino")
	private String cpfDestino;

	@JsonProperty("contaOrigem")
	private String contaOrigem;

	@JsonProperty("contaDestino")
	private String contaDestino;

	@JsonProperty("valor")
	private BigDecimal valor;

	public String getCpfOrigem() {
		return cpfOrigem;
	}

	public void setCpfOrigem(String cpfOrigem) {
		this.cpfOrigem = cpfOrigem;
	}

	public String getCpfDestino() {
		return cpfDestino;
	}

	public void setCpfDestino(String cpfDestino) {
		this.cpfDestino = cpfDestino;
	}

	public String getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
