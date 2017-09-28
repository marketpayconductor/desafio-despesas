package br.com.conductor.desafio.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransacaoRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("cpf")
	private String cpf;

	@JsonProperty("numeroConta")
	private String numeroConta;

	@JsonProperty("tipoOperacao")
	private Integer tipoOperacao;

	@JsonProperty("valor")
	private BigDecimal valor;

	@JsonProperty("observacao")
	private String observacao;

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

	public Integer getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(Integer tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
