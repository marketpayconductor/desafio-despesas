package br.com.conductor.desafiodespesas.enums;


/**
 * 
 * @author luiz
 *
 */

public enum Codigos {

	CODIGO_SUCESSO("00","OPERAÇÃO REALIZADA COM SUCESSO"),
	CONTA_INEXISTENTE("01", "CONTA NÃO ENCONTRADA"),
	CPF_NAO_PERTENCENTE_A_CONTA("02","CPF NÃO PERTECENTE A CONTA"),
	SALDO_INSUFICIENTE("03","SALDO INSUFICIENTE PARA REALIZAR ESSA TRANSAÇÃO"),
	ERRO_PROCESSAMENTO_INTERNO("99", "ERRO DE PROCESSAMENTO INTERNO");
	
	private String mensagem;
	private String codigo;
	
	private Codigos(String codigo, String mensagem) {
		this.mensagem = mensagem;
		this.codigo = codigo;
		
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
