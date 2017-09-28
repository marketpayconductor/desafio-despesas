package br.com.conductor.desafio.exception;

public class CpfNaoInformadoException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public CpfNaoInformadoException() {
		super("CPF não informado/inválido");
	}
}
