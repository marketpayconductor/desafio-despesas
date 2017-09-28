package br.com.conductor.desafio.dto;

import java.io.Serializable;

public class ResquestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String numeroConta;
	private String cpf;

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
