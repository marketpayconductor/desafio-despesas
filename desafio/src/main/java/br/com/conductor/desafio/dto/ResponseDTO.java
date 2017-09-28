package br.com.conductor.desafio.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class ResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo;
	private String mensagem;

	public ResponseDTO() {
	}

	public ResponseDTO(HttpStatus status, String mensagem) {
		this.codigo = status.toString() + " " + status.name();
		this.mensagem = mensagem;
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
