package br.com.conductor.desafio.util;

public enum TiposLancamento {
	
	RECEITA("RECEITA", "ENTRADA"),
	DESPESA("DESPESA", "SAÍDA"),
	TRANSFERENCIA_ENTRADA("TRASNFERÊNCIA ENTRADA", "ENTRADA"),
	TRANSFERENCIA_SAIDA("TRASNFERÊNCIA SAÍDA", "SAÍDA");
	
	private String nome;
	private String tipo;
	
	private TiposLancamento(String nome, String tipo){
		this.nome = nome;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}
	
	
}
