package br.com.conductor.despesasapp.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade que representa uma movimentação de uma conta.
 * 
 * @author alexsandro (alexsandroguedes@ffm.com.br) 
 *
 */
@Entity
@Table(name = "T_MOVIMENTO")
public class Movimento implements Serializable {
	
	private static final long serialVersionUID = -8582270490141716425L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_MOVIMENTO")
	private long idMovimento;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="ID_CONTA")
	private Conta conta;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="ID_TIPO_MOVIMENTO")
	@NotNull
	private TipoMovimento tipoMovimento;
	
	@Column(name = "OBSERVACAO")
	private String observacao;
	
	@NotNull
	@Column(name = "VALOR")
	private BigDecimal valor;
	
	@NotNull
	@Column(name = "DATA")
	private Date data;
	
	public static class Builder {
		private Conta conta;
		private TipoMovimento tipoMovimento;
		private String observacao;
		private BigDecimal valor;
		private Date data;
			
		public Builder(String observacao, Conta conta, BigDecimal valor) {
			this.conta = conta;
			this.data = new Date();
			this.observacao = observacao;
			this.valor = valor;
		}
		
		public Builder receitaOrDespesa(int codigoTipoMovimentacao){
			this.tipoMovimento = new TipoMovimento(codigoTipoMovimentacao);
			return this;
		}

		public Builder receita() {
			this.tipoMovimento = new TipoMovimento(TipoMovimento.RECEITA);
			return this;
		}
		
		public Builder despesa() {
			this.tipoMovimento = new TipoMovimento(TipoMovimento.DESPESA);
			return this;
		}
		
		public Movimento build() {
			return new Movimento(this);
		}
		
	}
	
	private Movimento(Builder builder) {
		super();
		this.conta = builder.conta;
		this.tipoMovimento = builder.tipoMovimento;
		this.observacao = builder.observacao;
		this.valor = builder.valor;
		this.data = builder.data;
	}
	
	public Movimento() {
	}

	public Conta getConta() {
		return conta;
	}
	
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public TipoMovimento getTipoMovimento() {
		return tipoMovimento;
	}
	
	public void setTipoMovimento(TipoMovimento tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
	
	public int getCodigoIdentificadorTipoMovimento() {
		return this.tipoMovimento.getCodigoIdentificador();
	}
	
	public boolean isMovimentoDebito() {
		return TipoMovimento.getTiposMovimentoDebito().contains(getCodigoIdentificadorTipoMovimento());
	}
	
	public boolean isMovimentoCredito() {
		return TipoMovimento.getTiposMovimentoCredito().contains(getCodigoIdentificadorTipoMovimento());
	}

}
