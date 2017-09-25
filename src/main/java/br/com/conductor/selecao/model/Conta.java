package br.com.conductor.selecao.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "T_CONTA")
public class Conta implements Serializable {

	private static final long serialVersionUID = 6315323841795199793L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@NotEmpty
	@Column(name = "BANCO", nullable = false)
	private String banco;

	@NotEmpty
	@Column(name = "AGENCIA", nullable = false)
	private String agencia;

	@NotEmpty
	@Column(name = "CONTA", nullable = false)
	private String conta;

	@Column(name = "DESCRICAO")
	private String descricao;

	@NotNull
	@Column(name = "SALDO", nullable = false)
	private BigDecimal saldo;

	@NotNull
	@Column(name = "SALDOANTERIOR", nullable = false)
	private BigDecimal saldoAlterior;

	public Conta() {
		super();
	}

	public Conta(Long id, String banco, String agencia, String conta, String descricao, BigDecimal saldo,
			BigDecimal saldoAlterior) {
		super();
		this.id = id;
		this.banco = banco;
		this.agencia = agencia;
		this.conta = conta;
		this.descricao = descricao;
		this.saldo = saldo;
		this.saldoAlterior = saldoAlterior;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getSaldoAlterior() {
		return saldoAlterior;
	}

	public void setSaldoAlterior(BigDecimal saldoAlterior) {
		this.saldoAlterior = saldoAlterior;
	}
	
	@PrePersist
	private void salvarValoresPadroes() {
		if(getSaldo() == null) {
			setSaldo(BigDecimal.ZERO);
		}
		
		if(getSaldoAlterior() == null) {
			setSaldoAlterior(BigDecimal.ZERO);
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
		Conta other = (Conta) obj;
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
		builder.append("Conta [id=");
		builder.append(id);
		builder.append(", banco=");
		builder.append(banco);
		builder.append(", agencia=");
		builder.append(agencia);
		builder.append(", conta=");
		builder.append(conta);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append(", saldo=");
		builder.append(saldo);
		builder.append(", saldoAlterior=");
		builder.append(saldoAlterior);
		builder.append("]");
		return builder.toString();
	}

}
