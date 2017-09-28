package br.com.conductor.desafio.util;

public enum TipoOperacao {

	RECEITA("Receita", "Crédito"),
	DESPESA("Despesa", "Débito"),
	TRANSFERENCIA_CREDITO("Transferencia Crédito", "Crédito"), 
	TRANSFERENCIA_DEBITO("Transferencia Débito", "Débito");

	private String tipo;
	private String nome;

	private TipoOperacao(String tipo, String nome) {
		this.tipo = tipo;
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
