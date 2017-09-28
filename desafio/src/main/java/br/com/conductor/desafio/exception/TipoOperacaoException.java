package br.com.conductor.desafio.exception;

public class TipoOperacaoException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public TipoOperacaoException() {
		super("Tipo de operação inválido ou não informado");
	}
}
