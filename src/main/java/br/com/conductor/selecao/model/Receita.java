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
@Table(name = "T_RECEITA")
public class Receita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@NotNull
	@Column(name = "DATACADASTRO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCadastro;

	@Column(name = "DATARECEBIMENTO")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataRecebimento;

	@Column(name = "DATACANCELAMENTO")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCancelamento;

	@NotNull
	@Digits(integer = 5, fraction = 2)
	@Column(name = "VALOR", nullable = false)
	private BigDecimal valor;

	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private StatusReceita status;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "TIPORECEITAID", nullable = false)
	private TipoReceita tipoReceita;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "USUARIOCADASTROID", nullable = false)
	private Usuario usuarioCadastro;

	@ManyToOne
	@JoinColumn(name = "USUARIORECEBIMENTOID")
	private Usuario usuarioRecebimento;

	@ManyToOne
	@JoinColumn(name = "USUARIOCANCELAMENTOID")
	private Usuario usuarioCancelamento;
	
	@NotNull	
	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;

	public Receita() {
		super();
	}
	
	public Receita(Long id, Calendar dataCadastro, Calendar dataRecebimento, Calendar dataCancelamento,
			BigDecimal valor, StatusReceita status, TipoReceita tipoReceita, Usuario usuarioCadastro,
			Usuario usuarioRecebimento, Usuario usuarioCancelamento, String descricao) {
		super();
		this.id = id;
		this.dataCadastro = dataCadastro;
		this.dataRecebimento = dataRecebimento;
		this.dataCancelamento = dataCancelamento;
		this.valor = valor;
		this.status = status;
		this.tipoReceita = tipoReceita;
		this.usuarioCadastro = usuarioCadastro;
		this.usuarioRecebimento = usuarioRecebimento;
		this.usuarioCancelamento = usuarioCancelamento;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Calendar getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Calendar dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
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

	public StatusReceita getStatus() {
		return status;
	}

	public void setStatus(StatusReceita status) {
		this.status = status;
	}

	public TipoReceita getTipoReceita() {
		return tipoReceita;
	}

	public void setTipoReceita(TipoReceita tipoReceita) {
		this.tipoReceita = tipoReceita;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Usuario getUsuarioRecebimento() {
		return usuarioRecebimento;
	}

	public void setUsuarioRecebimento(Usuario usuarioRecebimento) {
		this.usuarioRecebimento = usuarioRecebimento;
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
		if (getDataCadastro() == null) {
			setDataCadastro(Calendar.getInstance());
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
		Receita other = (Receita) obj;
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
		builder.append("Receita [id=");
		builder.append(id);
		builder.append(", dataCadastro=");
		builder.append(dataCadastro);
		builder.append(", dataRecebimento=");
		builder.append(dataRecebimento);
		builder.append(", dataCancelamento=");
		builder.append(dataCancelamento);
		builder.append(", valor=");
		builder.append(valor);
		builder.append(", status=");
		builder.append(status);
		builder.append(", tipoReceita=");
		builder.append(tipoReceita);
		builder.append(", usuarioCadastro=");
		builder.append(usuarioCadastro);
		builder.append(", usuarioRecebimento=");
		builder.append(usuarioRecebimento);
		builder.append(", usuarioCancelamento=");
		builder.append(usuarioCancelamento);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append("]");
		return builder.toString();
	}
	
}
