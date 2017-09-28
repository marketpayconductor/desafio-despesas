package br.com.conductor.desafio.exception;

public class NumeroContaNaoInformado extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public NumeroContaNaoInformado() {
		super("Número da conta não informado");
	}
}
