package br.com.conductor.despesasapp.api.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ConsultaSaldoRequestDTO implements Serializable {

	private static final long serialVersionUID = -5281768772502171631L;
	
	@NotNull(message = "campo.notnull.message")
	private long numeroConta;

	public long getNumeroConta() {
		return numeroConta;
	}
	
	public void setNumeroConta(long numeroConta) {
		this.numeroConta = numeroConta;
	}
	
}
