package br.com.conductor.desafio.exception;

public class ValorLancamentoException extends DesafioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8383616546697029851L;

	public ValorLancamentoException() {
		super("Valor do Lançamento tem que ser maior que 0");
	}
	
	

}
