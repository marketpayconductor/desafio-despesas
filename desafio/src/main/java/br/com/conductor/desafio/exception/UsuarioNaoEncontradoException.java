package br.com.conductor.desafio.exception;

public class UsuarioNaoEncontradoException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException() {
		super("Usuário não encontrado!");
	}
}
