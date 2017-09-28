package br.com.conductor.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestDTO {

	@JsonProperty("numeroConta")
	private Long numeroConta;

	@JsonProperty("numeroContaDebitar")
	private Long numeroContaDebitar;

	@JsonProperty("numeroContaCreditar")
	private Long numeroContaCreditar;

	@JsonProperty("nomeProprietario")
	private String nomeProprietario;

	@JsonProperty("cpf")
	private String cpf;

	@JsonProperty("saldo")
	private BigDecimal saldo;

	@JsonProperty("valor")
	private BigDecimal valor;

	public Long getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Long numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Long getNumeroContaDebitar() {
		return numeroContaDebitar;
	}

	public void setNumeroContaDebitar(Long numeroContaDebitar) {
		this.numeroContaDebitar = numeroContaDebitar;
	}

	public Long getNumeroContaCreditar() {
		return numeroContaCreditar;
	}

	public void setNumeroContaCreditar(Long numeroContaCreditar) {
		this.numeroContaCreditar = numeroContaCreditar;
	}

	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
