package br.com.conductor.despesasapp.api.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class TransferenciaRequestDTO implements Serializable {

	private static final long serialVersionUID = -4213913973056576029L;

	@NotNull(message = "campo.notnull.message")
	private long numeroContaOrigem;
	
	@NotNull(message = "campo.notnull.message")
	private String cpfContaOrigem;
	
	@NotNull(message = "campo.notnull.message")
	private long numeroContaDestino;
	
	@NotNull(message = "campo.notnull.message")
	private String cpfContaDestino;
	
	@NotNull(message = "campo.notnull.message")
	private BigDecimal valor;

	public long getNumeroContaOrigem() {
		return numeroContaOrigem;
	}

	public void setNumeroContaOrigem(long numeroContaOrigem) {
		this.numeroContaOrigem = numeroContaOrigem;
	}

	public String getCpfContaOrigem() {
		return cpfContaOrigem;
	}

	public void setCpfContaOrigem(String cpfContaOrigem) {
		this.cpfContaOrigem = cpfContaOrigem;
	}

	public long getNumeroContaDestino() {
		return numeroContaDestino;
	}

	public void setNumeroContaDestino(long numeroContaDestino) {
		this.numeroContaDestino = numeroContaDestino;
	}

	public String getCpfContaDestino() {
		return cpfContaDestino;
	}

	public void setCpfContaDestino(String cpfContaDestino) {
		this.cpfContaDestino = cpfContaDestino;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
