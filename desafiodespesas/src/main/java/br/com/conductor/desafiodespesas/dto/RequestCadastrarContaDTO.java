package br.com.conductor.desafiodespesas.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 
 * @author luiz
 *
 */

public class RequestCadastrarContaDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("cpf")
	private String cpf;
	
	@JsonProperty("numeroconta")
	private String numeroConta;	
	
	@JsonProperty("saldoconta")	
	private BigDecimal saldoConta;



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



	public BigDecimal getSaldoConta() {
		return saldoConta;
	}



	public void setSaldoConta(BigDecimal saldoConta) {
		this.saldoConta = saldoConta;
	}

}
