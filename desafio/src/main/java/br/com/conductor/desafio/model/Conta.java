package br.com.conductor.desafio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "T_CONTA")
public class Conta implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CONTA")
	private Long idConta;
	
	@OneToOne(optional = false)
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;
	
	@NotNull
	@Column(name= "NUMERO_CONTA", unique = true)
	private String numeroConta;
	
	@NotNull
	@Column(name = "SALDO")
	private BigDecimal saldo;
	
	@NotNull
	@Column(name = "ATIVA")
	private boolean ativa = true;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ABERTURA")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY HH:mm:ss")
	private Date dataAbertura;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_DESATIVADA")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY HH:mm:ss")
	private Date dataDesativada;
	
	public Conta() {
	}

	public Long getIdConta() {
		return idConta;
	}

	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public BigDecimal getSaldo() {
		return saldo.setScale(2, RoundingMode.HALF_EVEN);
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	public Date getDataDesativada() {
		return dataDesativada;
	}

	public void setDataDesativada(Date dataDesativada) {
		this.dataDesativada = dataDesativada;
	}

	@Override
	public String toString() {
		return "Conta [idConta=" + idConta + ", usuario=" + usuario + ", numeroConta=" + numeroConta + ", saldo="
				+ saldo + ", ativa=" + ativa + ", dataAbertura=" + dataAbertura + ", dataDesativada=" + dataDesativada
				+ "]";
	}
}
