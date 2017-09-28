package br.com.conductor.desafio.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.conductor.desafio.model.Usuario;

@JsonInclude(value = Include.NON_NULL)
public class UsuarioResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private ResponseDTO response;
	private List<Usuario> usuarios;

	public ResponseDTO getResponse() {
		return response;
	}

	public void setResponse(ResponseDTO response) {
		this.response = response;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
