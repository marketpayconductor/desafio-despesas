package br.com.conductor.selecao.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("M")
public class LancamentoContaMovimentacao extends LancamentoConta {

	@NotNull
	@ManyToOne
	@JoinColumn(name = "MOVIMENTACAOID")
	private MovimentacaoConta movimentacaoConta;

	public LancamentoContaMovimentacao() {
		super();
	}

	public LancamentoContaMovimentacao(Long id, String descricao, Conta conta, Calendar dataCadastro,
			BigDecimal valorLancamento, BigDecimal saldoAtual, TipoLancamentoConta tipoLancamentoConta,
			Usuario usuario, MovimentacaoConta movimentacaoConta) {
		super(id, descricao, conta, dataCadastro, valorLancamento, saldoAtual, tipoLancamentoConta, usuario);
		this.movimentacaoConta = movimentacaoConta;
	}	

	public MovimentacaoConta getMovimentacaoConta() {
		return movimentacaoConta;
	}

	public void setMovimentacaoConta(MovimentacaoConta movimentacaoConta) {
		this.movimentacaoConta = movimentacaoConta;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LancamentoContaMovimentacao [movimentacaoConta=");
		builder.append(movimentacaoConta);
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
