package br.com.conductor.desafiodespesas.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.conductor.desafiodespesas.domain.movimentacao.Movimentacao;


/**
 * 
 * @author luiz
 *
 */

public class ResponseDefaultApiDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoResposta;
	private String mensagemReposta;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)	
	private BigDecimal saldoAtual;
	
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private List<Movimentacao> lista;
	
	
	public ResponseDefaultApiDTO(String codigoResposta, String mensagemResposta) {
		this.codigoResposta = codigoResposta;
		this.mensagemReposta =mensagemResposta;
	}
	
	public ResponseDefaultApiDTO() {

	}

	public String getCodigoResposta() {
		return codigoResposta;
	}

	public void setCodigoResposta(String codigoResposta) {
		this.codigoResposta = codigoResposta;
	}

	public String getMensagemReposta() {
		return mensagemReposta;
	}

	public void setMensagemReposta(String mensagemReposta) {
		this.mensagemReposta = mensagemReposta;
	}

	public List<Movimentacao> getLista() {
		return lista;
	}

	public void setLista(List<Movimentacao> lista) {
		this.lista = lista;
	}
	
	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}
	
	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}
	

}
