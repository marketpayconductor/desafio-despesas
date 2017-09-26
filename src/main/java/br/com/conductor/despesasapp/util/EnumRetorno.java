package br.com.conductor.despesasapp.util;

import br.com.conductor.despesasapp.api.dto.response.RetornoDTO;

public enum EnumRetorno {

	OPERACAO_REALIZADA_COM_SUCESSO("00", "Operação realizada com sucesso!"),
	ERRO_INTERNO_NA_APLICAO("-1", "Ocorreu um erro interno na aplicação"),
	CONTA_INEXISTENTE("-2", "Conta inexistente"),
	INCONSISTENCIA_DE_DADOS("-3", "Inconsistência nos dados da requsição!"),
	VALOR_MOVIMENTACAO_NEGATIVO("-4", "Valor não pode ser negativo!");

	private String codigo;
	private String mensagem;


	EnumRetorno(String codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	public RetornoDTO parse() {
		return new RetornoDTO(this.codigo, this.mensagem);
	}
	
}
