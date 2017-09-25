package br.com.conductor.desafiodespesas.domain.movimentacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author luiz
 *
 */
@Entity
@Table(name = "MOVIMENTACAO")
public class Movimentacao implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDMOVIMENTACAO")
	private long idmovimentacao;
	
	@Column(name = "VALOROPERACAO")
	private BigDecimal valor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATAMOVIMENTACAO")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY HH:mm:ss")
	private Date dataMovimentacao;
	
	@Column(name ="OBSERVACAO")
	private String observacao;
	
	@Column(name ="TIPOOPERACAO")
	private String tipoOperacao;
	
	@Column(name ="CODIGOOPERACAO")
	private int codigoDespesa;	
	
	
	public Movimentacao() {
		
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}


	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public String getTipoOperacao() {
		return tipoOperacao;
	}


	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}


	public int getCodigoDespesa() {
		return codigoDespesa;
	}


	public void setCodigoDespesa(int codigoDespesa) {
		this.codigoDespesa = codigoDespesa;
	}	

	
	
}
