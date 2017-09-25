package br.com.conductor.selecao.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.conductor.selecao.exception.ParametroRequestInvalidoException;

public class SalvarAlterarReceitaDTO implements Serializable {

	private static final long serialVersionUID = -6705186236283220412L;

	private String valor;

	private long tipoReceita;

	private long usuarioCadastro;
	
	private String descricao;
	
	public SalvarAlterarReceitaDTO() {
		super();
	}	

	public SalvarAlterarReceitaDTO(String valor, long tipoReceita, long usuarioCadastro, String descricao) {
		super();
		this.valor = valor;
		this.tipoReceita = tipoReceita;
		this.usuarioCadastro = usuarioCadastro;
		this.descricao = descricao;
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
	
	public long getTipoReceita() {
		return tipoReceita;
	}

	public void setTipoReceita(long tipoReceita) {
		this.tipoReceita = tipoReceita;
	}

	public long getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(long usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
