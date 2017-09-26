package br.com.conductor.despesasapp.api.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class CadastroMovimentoRequestDTO  {

	@NotNull(message = "campo.notnull.message")
	private BigDecimal valor;
	
	@NotNull(message = "campo.notnull.message")
	private long numeroConta;
	
	@NotNull(message = "{campo.notnull.message")
	private String cpf;
	
	@NotNull(message = "campo.notnull.message")
	private int codigoTipoMovimentacao;
	
	@NotNull(message = "campo.notnull.message")
	private String observacao;
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public long getNumeroConta() {
		return numeroConta;
	}
	
	public void setNumeroConta(long numeroConta) {
		this.numeroConta = numeroConta;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public int getCodigoTipoMovimentacao() {
		return codigoTipoMovimentacao;
	}
	
	public void setCodigoTipoMovimentacao(int codigoTipoMovimentacao) {
		this.codigoTipoMovimentacao = codigoTipoMovimentacao;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
