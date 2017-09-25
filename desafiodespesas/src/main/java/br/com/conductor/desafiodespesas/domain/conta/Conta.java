package br.com.conductor.desafiodespesas.domain.conta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import br.com.conductor.desafiodespesas.domain.movimentacao.Movimentacao;

/**
 * 
 * @author luiz
 *
 */

@Entity
@Table (name ="CONTA")
public class Conta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDCONTA")
	private long idConta;

	@Column(name = "NUMEROCONTA", unique = true)
	private String numeroConta;
	
	@Column(name = "CPF", unique = true)
	private String cpf;
	
	@Column(name = "SALDOCONTA")
	private BigDecimal saldoConta;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<Movimentacao> listaDeMovimentacoes;
	
	public Conta() {

	}

	public long getIdConta() {
		return idConta;
	}

	public void setIdConta(long idConta) {
		this.idConta = idConta;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCpf() {
		return cpf;
	}
	public void setSaldoConta(BigDecimal saldoConta) {
		this.saldoConta = saldoConta;
	}
	
	public BigDecimal getSaldoConta() {
		return saldoConta;
	}
	
	
	public void setListaDeMovimentacoes(List<Movimentacao> listaDeMovimentacoes) {
		this.listaDeMovimentacoes = listaDeMovimentacoes;
	}
	
	public List<Movimentacao> getListaDeMovimentacoes() {
		return listaDeMovimentacoes;
	}	
	
}
