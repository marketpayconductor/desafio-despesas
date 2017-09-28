package br.com.conductor.desafio.exception;

public class UsuarioJaCadastradoException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public UsuarioJaCadastradoException() {
		super("CPF já cadastrado na base de dados");
	}
}
