package br.com.conductor.desafio.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.conductor.desafio.util.TiposLancamento;

@Entity
public class Lancamento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(optional=false)
	private Conta conta;
	
	@Column(name="descricao", nullable=false)
	private String descricao;
	
	@Column(name="valor", nullable=false)
	private BigDecimal valor;
	
	@Column(name="data", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Column(name="tipoLancamento", nullable=false)
	@Enumerated
    private TiposLancamento tipoLancamento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public TiposLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TiposLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	@Override
	public String toString() {
		return "Lancamento [idLancamento=" + id + ", idConta=" + conta.getIdConta() + ", numeroConta=" + conta.getNumeroConta() + ", descricao=" + descricao + ", valor=" + valor + ", data=" + data
				+ ", tipoLancamento=" + tipoLancamento + "]";
	}
}
