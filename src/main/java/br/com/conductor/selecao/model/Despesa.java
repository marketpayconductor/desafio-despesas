package br.com.conductor.selecao.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import br.com.conductor.selecao.util.FuncoesData;

@Entity
@Table(name = "T_DESPESA")
public class Despesa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@NotNull
	@Column(name = "DATAEMISSAO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataEmissao;

	@NotNull
	@Column(name = "DATAVENCIMENTO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataVencimento;

	@Column(name = "DATAPAGAMENTO")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataPagamento;

	@Column(name = "DATACANCELAMENTO")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCancelamento;

	@NotNull
	@Digits(integer = 5, fraction = 2)
	@Column(name = "VALOR", nullable = false)
	private BigDecimal valor;

	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private StatusDespesa status;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "TIPODESPESAID", nullable = false)
	private TipoDespesa tipoDespesa;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "USUARIOCADASTROID", nullable = false)
	private Usuario usuarioCadastro;

	@ManyToOne
	@JoinColumn(name = "USUARIOPAGAMENTOID")
	private Usuario usuarioPagamento;

	@ManyToOne
	@JoinColumn(name = "USUARIOCANCELAMENTOID")
	private Usuario usuarioCancelamento;
	
	@NotNull	
	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;

	public Despesa() {
		super();
	}
	
	public Despesa(Long id, Calendar dataEmissao, Calendar dataVencimento, Calendar dataPagamento,
			Calendar dataCancelamento, BigDecimal valor, StatusDespesa status, TipoDespesa tipoDespesa,
			Usuario usuarioCadastro, Usuario usuarioPagamento, Usuario usuarioCancelamento, String descricao) {
		super();
		this.id = id;
		this.dataEmissao = dataEmissao;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.dataCancelamento = dataCancelamento;
		this.valor = valor;
		this.status = status;
		this.tipoDespesa = tipoDespesa;
		this.usuarioCadastro = usuarioCadastro;
		this.usuarioPagamento = usuarioPagamento;
		this.usuarioCancelamento = usuarioCancelamento;
		this.descricao = descricao;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDataEmissao() {
		return dataEmissao;
	}
	
	@Transient
	public String getDataEmissaoAsString() {
		return FuncoesData.calendarToString(getDataEmissao());
	}

	public void setDataEmissao(Calendar dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Calendar getDataVencimento() {
		return dataVencimento;
	}
	
	@Transient
	public String getDataVencimentoAsString() {
		return FuncoesData.calendarToString(getDataVencimento());
	}
	
	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}
	
	@Transient
	public String getDataPagamentoAsString() {
		return FuncoesData.calendarToString(getDataPagamento());
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Calendar getDataCancelamento() {
		return dataCancelamento;
	}
	
	@Transient
	public String getDataCancelamentoAsString() {
		return FuncoesData.calendarToString(getDataCancelamento());
	}

	public void setDataCancelamento(Calendar dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public StatusDespesa getStatus() {
		return status;
	}

	public void setStatus(StatusDespesa status) {
		this.status = status;
	}	

	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesa tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Usuario getUsuarioPagamento() {
		return usuarioPagamento;
	}

	public void setUsuarioPagamento(Usuario usuarioPagamento) {
		this.usuarioPagamento = usuarioPagamento;
	}

	public Usuario getUsuarioCancelamento() {
		return usuarioCancelamento;
	}

	public void setUsuarioCancelamento(Usuario usuarioCancelamento) {
		this.usuarioCancelamento = usuarioCancelamento;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@PrePersist
	private void salvarValoresPadroes() {
		if (getDataEmissao() == null) {
			setDataEmissao(Calendar.getInstance());
		}

		if (getDataVencimento() == null) {
			setDataVencimento(Calendar.getInstance());
		}

		if (getValor() == null) {
			setValor(BigDecimal.ZERO);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Despesa other = (Despesa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Despesa [id=");
		builder.append(id);
		builder.append(", dataEmissao=");
		builder.append(dataEmissao);
		builder.append(", dataVencimento=");
		builder.append(dataVencimento);
		builder.append(", dataPagamento=");
		builder.append(dataPagamento);
		builder.append(", dataCancelamento=");
		builder.append(dataCancelamento);
		builder.append(", valor=");
		builder.append(valor);
		builder.append(", status=");
		builder.append(status);
		builder.append(", tipoDespesa=");
		builder.append(tipoDespesa);
		builder.append(", usuarioCadastro=");
		builder.append(usuarioCadastro);
		builder.append(", usuarioPagamento=");
		builder.append(usuarioPagamento);
		builder.append(", usuarioCancelamento=");
		builder.append(usuarioCancelamento);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append(", getId()=");
		builder.append(getId());
		builder.append(", getDataEmissao()=");
		builder.append(getDataEmissao());
		builder.append(", getDataEmissaoAsString()=");
		builder.append(getDataEmissaoAsString());
		builder.append(", getDataVencimento()=");
		builder.append(getDataVencimento());
		builder.append(", getDataVencimentoAsString()=");
		builder.append(getDataVencimentoAsString());
		builder.append(", getDataPagamento()=");
		builder.append(getDataPagamento());
		builder.append(", getDataPagamentoAsString()=");
		builder.append(getDataPagamentoAsString());
		builder.append(", getDataCancelamento()=");
		builder.append(getDataCancelamento());
		builder.append(", getDataCancelamentoAsString()=");
		builder.append(getDataCancelamentoAsString());
		builder.append(", getValor()=");
		builder.append(getValor());
		builder.append(", getStatus()=");
		builder.append(getStatus());
		builder.append(", getTipoDespesa()=");
		builder.append(getTipoDespesa());
		builder.append(", getUsuarioCadastro()=");
		builder.append(getUsuarioCadastro());
		builder.append(", getUsuarioPagamento()=");
		builder.append(getUsuarioPagamento());
		builder.append(", getUsuarioCancelamento()=");
		builder.append(getUsuarioCancelamento());
		builder.append(", getDescricao()=");
		builder.append(getDescricao());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
}
