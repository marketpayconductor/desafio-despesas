package br.com.conductor.selecao.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.conductor.selecao.exception.ParametroRequestInvalidoException;

public class SalvarMovimentacaoContaDTO implements Serializable {
	
	private static final long serialVersionUID = 6677247224772695173L;

	private long codigoContaOrigem;
	
	private long codigoContaDestino;
	
	private long codigoUsuario;
	
	private String valor;
	
	private String descricao;

	public SalvarMovimentacaoContaDTO() {
		super();
	}

	public SalvarMovimentacaoContaDTO(long codigoContaOrigem, long codigoContaDestino, long codigoUsuario, String valor,
			String descricao) {
		super();
		this.codigoContaOrigem = codigoContaOrigem;
		this.codigoContaDestino = codigoContaDestino;
		this.codigoUsuario = codigoUsuario;
		this.valor = valor;
		this.descricao = descricao;
	}

	public long getCodigoContaOrigem() {
		return codigoContaOrigem;
	}

	public void setCodigoContaOrigem(long codigoContaOrigem) {
		this.codigoContaOrigem = codigoContaOrigem;
	}

	public long getCodigoContaDestino() {
		return codigoContaDestino;
	}

	public void setCodigoContaDestino(long codigoContaDestino) {
		this.codigoContaDestino = codigoContaDestino;
	}

	public long getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getValor() {
		return valor;
	}
	
	public BigDecimal getValorAsBigDecimal() throws ParametroRequestInvalidoException {
		if (getValor() == null) {
			return BigDecimal.ZERO;
		}

		try {
			return new BigDecimal(getValor());
		} catch (Exception e) {
			throw new ParametroRequestInvalidoException(e, "valor");
		}

	}

	public void setValor(String valor) {
		this.valor = valor;
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
		builder.append("SalvarMovimentacaoContaDTO [codigoContaOrigem=");
		builder.append(codigoContaOrigem);
		builder.append(", codigoContaDestino=");
		builder.append(codigoContaDestino);
		builder.append(", codigoUsuario=");
		builder.append(codigoUsuario);
		builder.append(", valor=");
		builder.append(valor);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append("]");
		return builder.toString();
	}
	
}
