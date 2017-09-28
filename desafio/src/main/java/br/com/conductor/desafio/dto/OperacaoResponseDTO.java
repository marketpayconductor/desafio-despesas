package br.com.conductor.desafio.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.conductor.desafio.util.TipoOperacao;

@JsonInclude(value = Include.NON_NULL)
public class OperacaoResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idOperacao;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY HH:mm:ss")
	private Date dataOperacao;
	private TipoOperacao tipoOperacao;
	private BigDecimal valorOperacao;
	private String observacao;

	public Long getIdOperacao() {
		return idOperacao;
	}

	public void setIdOperacao(Long idOperacao) {
		this.idOperacao = idOperacao;
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
}
