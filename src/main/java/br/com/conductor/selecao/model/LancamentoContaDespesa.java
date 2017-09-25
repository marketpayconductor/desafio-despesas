package br.com.conductor.selecao.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("D")
public class LancamentoContaDespesa extends LancamentoConta {

	@NotNull
	@ManyToOne
	@JoinColumn(name = "DESPESAID")
	private Despesa despesa;

	public LancamentoContaDespesa() {
		super();
	}

	public LancamentoContaDespesa(Long id, String descricao, Conta conta, Calendar dataCadastro,
			BigDecimal valorLancamento, BigDecimal saldoAtual, TipoLancamentoConta tipoLancamentoConta, Usuario usuario,
			Despesa despesa) {
		super(id, descricao, conta, dataCadastro, valorLancamento, saldoAtual, tipoLancamentoConta, usuario);
		this.despesa = despesa;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LancamentoContaDespesa [despesa=");
		builder.append(despesa);
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
