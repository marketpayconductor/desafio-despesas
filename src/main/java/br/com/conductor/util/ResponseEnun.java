package br.com.conductor.util;

public enum ResponseEnun {
	
	SUCESSO("00", "Realizado com sucesso"),
	SALDO_ATUAL("01", "Salto Atual: "),
	DESPESA("02", "Despesa"),
	RECEITA("03", "Receita"),
	TRANSFERENCIA("04", "Transferência"),
	CONTA_DUPLICADA("96", "A conta já existe"),
	CONTA_NAO_EXISTE("97","Número de Conta inexistente"),
	SEM_LANCAMENTO("98", "Sem Lancamentos para esta conta"),
	ERRO("99", "Erro Interno");
	
	private String codigo;
	private String mensagem;
	

	private ResponseEnun(String codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getMensagem() {
		return mensagem;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
