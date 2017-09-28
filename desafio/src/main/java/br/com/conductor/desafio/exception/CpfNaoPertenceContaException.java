package br.com.conductor.desafio.exception;

public class CpfNaoPertenceContaException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public CpfNaoPertenceContaException() {
		super("CPF informado não pertece a conta informada");
	}
}
