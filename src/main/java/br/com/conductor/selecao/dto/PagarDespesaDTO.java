package br.com.conductor.selecao.dto;

import br.com.conductor.selecao.model.Conta;
import br.com.conductor.selecao.model.Usuario;

public class PagarDespesaDTO {
	
	private Long usuarioPagamento;
	
	private Long conta;

	public PagarDespesaDTO() {
		super();
	}

	public PagarDespesaDTO(Long usuarioPagamento, Long conta) {
		super();
		this.usuarioPagamento = usuarioPagamento;
		this.conta = conta;
	}
	
	public Long getUsuarioPagamento() {
		return usuarioPagamento;
	}
	
	public Usuario getUsuarioPagamentoaAsUsuario() {
		if(getUsuarioPagamento() == null) {
			return null;
		}
		
		Usuario usuario = new Usuario();
		usuario.setId(getUsuarioPagamento());
		
		return usuario;
	}

	public void setUsuarioPagamento(Long usuarioPagamento) {
		this.usuarioPagamento = usuarioPagamento;
	}

	public Long getConta() {
		return conta;
	}
	
	public Conta getContaAsConta() {
		if(getConta() == null) {
			return null;
		}
		
		Conta conta = new Conta();
		conta.setId(getConta());
		return conta;
	}

	public void setConta(Long conta) {
		this.conta = conta;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PagarReceitaDTO [usuarioPagamento=");
		builder.append(usuarioPagamento);
		builder.append(", conta=");
		builder.append(conta);
		builder.append("]");
		return builder.toString();
	}
	
}
