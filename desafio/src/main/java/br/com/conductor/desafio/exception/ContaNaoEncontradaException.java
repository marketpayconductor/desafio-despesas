package br.com.conductor.desafio.exception;

public class ContaNaoEncontradaException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public ContaNaoEncontradaException() {
		super("Conta não encontrada");
	}
}
