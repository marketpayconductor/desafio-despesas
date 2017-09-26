package br.com.conductor.desafio.exception;

public class ContaNaoPossuiFundosException extends DesafioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8383616546697029851L;

	public ContaNaoPossuiFundosException() {
		super("Conta Não Possui Fundos para a Transferência");
	}
	
	

}
