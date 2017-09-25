package br.com.conductor.selecao.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import br.com.conductor.selecao.exception.ParametroRequestInvalidoException;
import br.com.conductor.selecao.util.FuncoesData;

public class SalvarAlterarDespesaDTO implements Serializable {

	private static final long serialVersionUID = -6705186236283220412L;

	private String dataVencimento;

	private String valor;

	private Long tipoDespesa;

	private Long usuarioCadastro;
	
	private String descricao;
	
	public SalvarAlterarDespesaDTO() {
		super();
	}
	
	public SalvarAlterarDespesaDTO(String dataVencimento, String valor, Long tipoDespesa, Long usuarioCadastro,
			String descricao) {
		super();
		this.dataVencimento = dataVencimento;
		this.valor = valor;
		this.tipoDespesa = tipoDespesa;
		this.usuarioCadastro = usuarioCadastro;
		this.descricao = descricao;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public Calendar getDataVencimentoAsCalendar() throws ParametroRequestInvalidoException {
		Date dateVencimento;
		if (getDataVencimento() == null) {
			dateVencimento = new Date();
		} else {
			try {
				dateVencimento = FuncoesData.stringToDate(getDataVencimento());
			} catch (ParseException e) {
				throw new ParametroRequestInvalidoException(e, "dateVencimento");
			}
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateVencimento);

		return calendar;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
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
	
	public Long getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(Long tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public Long getUsuarioCadastro() {
		return usuarioCadastro;
	}
	
	public void setUsuarioCadastro(Long usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
