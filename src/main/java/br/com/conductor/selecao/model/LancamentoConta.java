package br.com.conductor.selecao.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "T_LANCAMENTOCONTA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPOLANCAMENTO")
public class LancamentoConta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull	
	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "CONTAID", nullable = false)
	private Conta conta;
	
	@NotNull
	@Column(name = "DATACADASTRO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCadastro;
	
	@NotNull
	@Digits(integer = 5, fraction = 2)
	@Column(name = "VALORLANCAMENTO", nullable = false)
	private BigDecimal valorLancamento;
	
	@NotNull
	@Digits(integer = 5, fraction = 2)
	@Column(name = "SALDOATUAL", nullable = false)
	private BigDecimal saldoAtual;
	
	@Column(name = "TIPOLANCAMENTOCONTA", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TipoLancamentoConta tipoLancamentoConta;
	
	@ManyToOne
	@JoinColumn(name = "USUARIOID", nullable = false)
	private Usuario usuario;

	public LancamentoConta() {
		super();
	}

	public LancamentoConta(Long id, String descricao, Conta conta, Calendar dataCadastro, BigDecimal valorLancamento,
			BigDecimal saldoAtual, TipoLancamentoConta tipoLancamentoConta, Usuario usuario) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.conta = conta;
		this.dataCadastro = dataCadastro;
		this.valorLancamento = valorLancamento;
		this.saldoAtual = saldoAtual;
		this.tipoLancamentoConta = tipoLancamentoConta;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public BigDecimal getValorLancamento() {
		return valorLancamento;
	}

	public void setValorLancamento(BigDecimal valorLancamento) {
		this.valorLancamento = valorLancamento;
	}

	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public TipoLancamentoConta getTipoLancamentoConta() {
		return tipoLancamentoConta;
	}

	public void setTipoLancamentoConta(TipoLancamentoConta tipoLancamentoConta) {
		this.tipoLancamentoConta = tipoLancamentoConta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		LancamentoConta other = (LancamentoConta) obj;
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
		builder.append("LancamentoConta [id=");
		builder.append(id);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append(", conta=");
		builder.append(conta);
		builder.append(", dataCadastro=");
		builder.append(dataCadastro);
		builder.append(", valorLancamento=");
		builder.append(valorLancamento);
		builder.append(", saldoAtual=");
		builder.append(saldoAtual);
		builder.append(", tipoLancamentoConta=");
		builder.append(tipoLancamentoConta);
		builder.append(", usuario=");
		builder.append(usuario);
		builder.append("]");
		return builder.toString();
	}
	
}
