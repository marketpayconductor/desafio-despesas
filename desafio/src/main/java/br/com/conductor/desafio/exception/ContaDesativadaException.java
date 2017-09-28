package br.com.conductor.desafio.exception;

public class ContaDesativadaException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public ContaDesativadaException() {
		super("Ação não permitida. Conta informada esta desativada");
	}
}
