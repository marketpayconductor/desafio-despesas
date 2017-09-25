package br.com.conductor.selecao.exception;

public class ParametroRequestInvalidoException extends Exception {
	
	private static final long serialVersionUID = -2448707600952992574L;
	
	private String campo;
	
	public ParametroRequestInvalidoException(String campo) {
		super();
		this.campo = campo;
	}

	public ParametroRequestInvalidoException(Throwable cause, String campo) {
		super(cause);
		setCampo(campo);
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	public String getMensagemParametroInvalido() {
		return "Param\u00E2tro inv\u00E1lido: " + getCampo();
	}

}
