package br.com.conductor.desafio.exception;

public class SaldoIndisponivelException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public SaldoIndisponivelException() {
		super("Saldo insuficiente para a operação");
	}

}
