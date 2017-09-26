package br.com.conductor.despesasapp.api.dto.response;

import java.io.Serializable;

public class RetornoDTO implements Serializable {
	
	private static final long serialVersionUID = 3659001864166861713L;
	
	private String codigo;
	private String mensagem;
	
	public RetornoDTO(String codigo, String mensagem) {
		this.mensagem = mensagem;
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
