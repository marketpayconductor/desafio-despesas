package br.com.conductor.desafio.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ExtratoOperacaoResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String usuario;
	private String numeroConta;
	private String cpf;
	private BigDecimal saldoAtual;
	private List<OperacaoResponseDTO> operacoes;
	private ResponseDTO response;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

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

	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public List<OperacaoResponseDTO> getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(List<OperacaoResponseDTO> operacoes) {
		this.operacoes = operacoes;
	}

	public ResponseDTO getResponse() {
		return response;
	}

	public void setResponse(ResponseDTO response) {
		this.response = response;
	}
}
