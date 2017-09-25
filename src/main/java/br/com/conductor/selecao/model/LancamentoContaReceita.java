package br.com.conductor.selecao.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("R")
public class LancamentoContaReceita extends LancamentoConta {

	@NotNull
	@ManyToOne
	@JoinColumn(name = "RECEITAID")
	private Receita receita;

	public LancamentoContaReceita() {
		super();
	}

	public LancamentoContaReceita(Long id, String descricao, Conta conta, Calendar dataCadastro,
			BigDecimal valorLancamento, BigDecimal saldoAtual, TipoLancamentoConta tipoLancamentoConta,
			Usuario usuario, Receita receita) {
		super(id, descricao, conta, dataCadastro, valorLancamento, saldoAtual, tipoLancamentoConta, usuario);
		this.receita = receita;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LancamentoContaReceita [receita=");
		builder.append(receita);
		builder.append(", getId()=");
		builder.append(getId());
		builder.append(", getDescricao()=");
		builder.append(getDescricao());
		builder.append(", getConta()=");
		builder.append(getConta());
		builder.append(", getDataCadastro()=");
		builder.append(getDataCadastro());
		builder.append(", getValorLancamento()=");
		builder.append(getValorLancamento());
		builder.append(", getSaldoAtual()=");
		builder.append(getSaldoAtual());
		builder.append(", getTipoLancamentoConta()=");
		builder.append(getTipoLancamentoConta());
		builder.append(", getUsuario()=");
		builder.append(getUsuario());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append("]");
		return builder.toString();
	}
	
}
