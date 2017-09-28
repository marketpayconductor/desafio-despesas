package br.com.conductor.desafio.exception;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;

	public ApplicationException(String mensagem) {
		super(mensagem);
	}
}
