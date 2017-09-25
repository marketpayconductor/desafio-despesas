package br.com.conductor.despesasapp.domain.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade que representa um tipo de movimentação.
 * 
 * @author alexsandro (alexsandroguedes@ffm.com.br) 
 *
 */
@Entity
@Table(name = "T_TIPOMOVIMENTACAO")
public class TipoMovimento implements Serializable {
	
	private static final long serialVersionUID = 8338604609466590405L;
	
	public static String CREDITO = "C";
	public static String DEBITO = "D";
	
	public static int DESPESA = -10;
	public static int RECEITA = -20;
	public static int TRANSFERENCIA_SALDO_DEBITO = -30;
	public static int TRANSFERENCIA_SALDO_CREDITO = -40;
	
	public static String OBS_TRANSFERENCIA_SALDO_PARA_OUTRA_CONTA = "Transferência de saldo para outra conta";
	public static String OBS_TRANSFERENCIA_SALDO_DE_OUTRA_CONTA = "Transferência de saldo de outra conta";
	
	@Id
	@Column(name = "ID_TIPO_MOVIMENTO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idTipoMovimento;
	
	@NotNull
	@Column(name = "CODIGO_IDENTIFICADOR", unique = true)
	private int codigoIdentificador;
	
	@NotNull
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@NotNull
	@Column(name = "CREDITO_DEBITO")
	private String creditoDebito;

	public TipoMovimento() {
	}
	
	public TipoMovimento(int codigoTipoMovimento) {
		this.codigoIdentificador = codigoTipoMovimento;
	}
	
	public long getIdTipoMovimento() {
		return idTipoMovimento;
	}
	
	public void setIdTipoMovimento(long idTipoMovimento) {
		this.idTipoMovimento = idTipoMovimento;
	}

	public int getCodigoIdentificador() {
		return codigoIdentificador;
	}

	public void setCodigoIdentificador(int codigoIdentificador) {
		this.codigoIdentificador = codigoIdentificador;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCreditoDebito() {
		return creditoDebito;
	}
	
	public void setCreditoDebito(String creditoDebito) {
		this.creditoDebito = creditoDebito;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public static List<Integer> getTiposMovimentoDebito() {
		return Arrays.asList(TRANSFERENCIA_SALDO_DEBITO, DESPESA);
	}
	
	public static List<Integer> getTiposMovimentoCredito() {
		return Arrays.asList(TRANSFERENCIA_SALDO_CREDITO, RECEITA);
	}
	
}
