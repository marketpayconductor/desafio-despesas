package br.com.conductor.desafio.exception;

public class UsariosNaoCadastradosException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public UsariosNaoCadastradosException() {
		super("Não há usuários cadastrados");
	}
}
