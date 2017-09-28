package br.com.conductor.desafio.exception;

public class ValorNaoInformado extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public ValorNaoInformado() {
		super("O valor informado dever ser maior que 0 (zero)");
	}
}
