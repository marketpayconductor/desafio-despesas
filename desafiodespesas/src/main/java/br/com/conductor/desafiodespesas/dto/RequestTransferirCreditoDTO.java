package br.com.conductor.desafiodespesas.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author luiz
 *
 */

public class RequestTransferirCreditoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("cpfContaAreceber")
	private String cpfContaAreceber;
	
	@JsonProperty("cpfContaAtransferir")	
	private String cpfContaAtransferir;
	
	
	@JsonProperty("numeroContaAreceber")
	private String numeroContaAreceber;
	
	@JsonProperty("numeroContaAtransferir")
	private String numeroContaAtransferir;
	
	@JsonProperty("valorTransferencia")
	private BigDecimal valorTransferencia;
	
	
	public String getCpfContaAreceber() {
		return cpfContaAreceber;
	}
	public void setCpfContaAreceber(String cpfContaAreceber) {
		this.cpfContaAreceber = cpfContaAreceber;
	}
	public String getCpfContaAtransferir() {
		return cpfContaAtransferir;
	}
	public void setCpfContaAtransferir(String cpfContaAtransferir) {
		this.cpfContaAtransferir = cpfContaAtransferir;
	}
	public String getNumeroContaAreceber() {
		return numeroContaAreceber;
	}
	public void setNumeroContaAreceber(String numeroContaAreceber) {
		this.numeroContaAreceber = numeroContaAreceber;
	}
	public String getNumeroContaAtransferir() {
		return numeroContaAtransferir;
	}
	public void setNumeroContaAtransferir(String numeroContaAtransferir) {
		this.numeroContaAtransferir = numeroContaAtransferir;
	}	
	public void setValorTransferencia(BigDecimal valorTransferencia) {
		this.valorTransferencia = valorTransferencia;
	}
	public BigDecimal getValorTransferencia() {
		return valorTransferencia;
	}
	
}
