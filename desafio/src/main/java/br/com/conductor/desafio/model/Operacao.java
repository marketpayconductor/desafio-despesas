package br.com.conductor.desafio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.conductor.desafio.util.TipoOperacao;

@Entity
@Table(name = "T_OPERACAO")
public class Operacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_OPERACAO")
	private Long idOperacao;
	
	@NotNull
	@ManyToOne(optional=false)
	@JoinColumn(name="CONTA")
	private Conta conta;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_OPERACAO")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY HH:mm:ss")
	private Date dataOperacao;
	
	@NotNull
	@Column(name="TIPO_OPERACAO")
	@Enumerated
	private TipoOperacao tipoOperacao;
	
	@NotNull
	@Column(name = "VALOR_OPERACAO")
	private BigDecimal valorOperacao;
	
	@NotNull
	@Column(name = "OBSERVACAO")
	private String observacao;

	public Operacao() {
	}

	public Long getIdOperacao() {
		return idOperacao;
	}

	public void setIdOperacao(Long idOperacao) {
		this.idOperacao = idOperacao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Date getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public BigDecimal getValorOperacao() {
		return valorOperacao;
	}

	public void setValorOperacao(BigDecimal valorOperacao) {
		this.valorOperacao = valorOperacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "Operacao [idOperacao=" + idOperacao + ", conta=" + conta + ", dataOperacao=" + dataOperacao
				+ ", tipoOperacao=" + tipoOperacao + ", valorOperacao=" + valorOperacao + ", observacao=" + observacao
				+ "]";
	}
}
