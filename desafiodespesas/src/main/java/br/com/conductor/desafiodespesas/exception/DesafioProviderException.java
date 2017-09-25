package br.com.conductor.desafiodespesas.exception;


/**
 * 
 * @author luiz
 *
 */

public class DesafioProviderException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoErro;
	private String mensagemErro;
	
	public DesafioProviderException(String codigo, String mensagem) {
		this.codigoErro = codigo;
		this.mensagemErro = mensagem;
	}

	public String getCodigoErro() {
		return codigoErro;
	}

	public void setCodigoErro(String codigoErro) {
		this.codigoErro = codigoErro;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}	
	
}
