package br.com.conductor.desafiodespesas.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author luiz
 *
 */

public class RequestDefaultApiDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("cpf")
	private String cpf;
	
	@JsonProperty("numeroconta")
	private String numeroConta;
	
	@JsonProperty("codigooperacao")
	private int codigoopeacao;
	
	@JsonProperty("valor")	
	private BigDecimal valor;
		
	@JsonProperty("observacao")	
	private String observacao;
	
	public RequestDefaultApiDTO() {

	}

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

	public void setCodigoopeacao(int codigoopeacao) {
		this.codigoopeacao = codigoopeacao;
	}
	
	public int getCodigoopeacao() {
		return codigoopeacao;
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
