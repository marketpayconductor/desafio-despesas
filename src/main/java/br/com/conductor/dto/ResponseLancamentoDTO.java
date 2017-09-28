package br.com.conductor.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseLancamentoDTO {

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/YYYY HH:mm:ss")
	private Date dataLancamento;

	private String descricao;

	private BigDecimal valor;

	private String codigo;
	
	private String mensagem;

	public ResponseLancamentoDTO() {
	}

	public ResponseLancamentoDTO(String codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
		
	}

	public ResponseLancamentoDTO(Date dataLancamento, String descricao, BigDecimal valor) {
		this.dataLancamento = dataLancamento;
		this.descricao = descricao;
		this.valor = valor;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
