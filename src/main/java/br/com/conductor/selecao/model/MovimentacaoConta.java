package br.com.conductor.selecao.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "T_MOVIMENTACAOCONTA")
public class MovimentacaoConta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull	
	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;
	
	@Column(name = "DATACADASTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCadastro;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "USUARIOID", nullable = false)
	private Usuario usuarioCadastro;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "CONTAORIGEMID", nullable = false)
	private Conta contaOrigem;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "CONTADESTINOID", nullable = false)
	private Conta contaDestino;
	
	@NotNull
	@Digits(integer = 5, fraction = 2)
	@Column(name = "VALOR", nullable = false)
	private BigDecimal valor;
	
	public MovimentacaoConta() {
		super();
	}

	public MovimentacaoConta(Long id, String descricao, Calendar dataCadastro, Usuario usuarioCadastro,
			Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataCadastro = dataCadastro;
		this.usuarioCadastro = usuarioCadastro;
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valor = valor;
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

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	

}
