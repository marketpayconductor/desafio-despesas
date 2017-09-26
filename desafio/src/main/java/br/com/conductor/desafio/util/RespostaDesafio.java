package br.com.conductor.desafio.util;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class RespostaDesafio implements Serializable {
	
	public RespostaDesafio() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -224687995005236395L;
	
	String codigo;
	String mensagem;
	Object objetoRetorno;
	
	
	public RespostaDesafio(HttpStatus status, String mensagem, Object objetoRetorno) {
		super();
		this.codigo = status.toString() + " - " + status.name();
		this.mensagem = mensagem;
		
		if(objetoRetorno == null)
			objetoRetorno = "";
		
		this.objetoRetorno = objetoRetorno;
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


	public Object getObjetoRetorno() {
		return objetoRetorno;
	}


	public void setObjetoRetorno(Object objetoRetorno) {
		this.objetoRetorno = objetoRetorno;
	}
	
}
