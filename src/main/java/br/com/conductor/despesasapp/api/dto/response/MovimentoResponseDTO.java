package br.com.conductor.despesasapp.api.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.conductor.despesasapp.domain.model.Movimento;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MovimentoResponseDTO implements Serializable {

	private static final long serialVersionUID = 1408497662478276052L;
	
	@JsonFormat(pattern="dd/MM/yyy")
	private Date data;
	private String observacao;
	private int codigoTipoMovimentacao;
	private BigDecimal valor;

	public MovimentoResponseDTO() {
	}
	
	public MovimentoResponseDTO(Movimento movimento) {
		this.data = movimento.getData();
		this.codigoTipoMovimentacao = movimento.getCodigoIdentificadorTipoMovimento();
		this.observacao = movimento.getObservacao();
		this.valor = movimento.getValor();
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public int getCodigoTipoMovimentacao() {
		return codigoTipoMovimentacao;
	}
	
	public void setCodigoTipoMovimentacao(int codigoTipoMovimentacao) {
		this.codigoTipoMovimentacao = codigoTipoMovimentacao;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
