package br.com.conductor.selecao.exception;

public class EntidadeNaoEncontrada extends Exception {

	private static final long serialVersionUID = 5061304360777906296L;
	
	private Class<?> classe;

	public EntidadeNaoEncontrada(Class<?> classe) {
		super();
		this.classe = classe;
	}

	public EntidadeNaoEncontrada(Throwable cause, Class<?> classe) {
		super(cause);
		this.classe = classe;
	}

	public Class<?> getClasse() {
		return classe;
	}

	public void setClasse(Class<?> classe) {
		this.classe = classe;
	}
	
	public String getMensagemEntidadeNaoEncontrada() {
		String str = null;
		if(getClasse() == null) {
			str = "Entidade";
		} else {
			String[] array = getClasse().getSimpleName().split("(?=\\p{Upper})");
			str = String.join(" ", array);			
		}
		
		
		return str + " n\u00E3o encontrado(a)";
	}

}
