package br.com.conductor.desafio.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbrirContaRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("cpf")
	private String cpf;

	@JsonProperty("numeroConta")
	private String numeroConta;

	@JsonProperty("valor")
	private BigDecimal valor;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
