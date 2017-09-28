package br.com.conductor.desafio.exception;

public class UsuarioDesativadoException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public UsuarioDesativadoException() {
		super("Ação não permitida, usuário desativado");
	}
}
