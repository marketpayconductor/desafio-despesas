package br.com.conductor.despesasapp.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade que representa uma conta.
 * 
 * @author alexsandro (alexsandroguedes@ffm.com.br) 
 *
 */
@Entity
@Table(name = "T_CONTA")
public class Conta implements Serializable {

	private static final long serialVersionUID = 7978362877422103591L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CONTA")
	private long idConta;

	@OneToOne(optional = false)
	@JoinColumn(name="ID_PESSOA")
	private Pessoa pessoa;

	@Column(name = "NUMERO", unique = true)
	private long numero;

	@Column(name = "SALDO_ATUAL")
	@NotNull
	private BigDecimal saldoAtual;

	@Column(name = "SALDO_ANTERIOR")
	@NotNull
	private BigDecimal saldoAnterior;

	public Conta() {
	}
	
	public Conta(int numeroConta, BigDecimal saldoAnterior, BigDecimal saldoAtual) {
		this.numero = numeroConta;
		this.saldoAnterior = saldoAnterior;
		this.saldoAtual = saldoAtual;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public BigDecimal getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(BigDecimal saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

}
