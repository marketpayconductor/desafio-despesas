package br.com.conductor.selecao.dto;

public class SalvarAlterarContaDTO {

	private String banco;

	private String agencia;

	private String conta;

	private String descricao;
	
	public SalvarAlterarContaDTO() {
		super();
	}

	public SalvarAlterarContaDTO(String banco, String agencia, String conta, String descricao) {
		super();
		this.banco = banco;
		this.agencia = agencia;
		this.conta = conta;
		this.descricao = descricao;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SalvarAlterarConta [banco=");
		builder.append(banco);
		builder.append(", agencia=");
		builder.append(agencia);
		builder.append(", conta=");
		builder.append(conta);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append("]");
		return builder.toString();
	}
	
	
}
