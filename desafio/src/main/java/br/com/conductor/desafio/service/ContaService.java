package br.com.conductor.desafio.service;

import java.math.BigDecimal;

import br.com.conductor.desafio.dto.ConsultarSaldoResponseDTO;
import br.com.conductor.desafio.dto.ExtratoOperacaoResponseDTO;
import br.com.conductor.desafio.dto.ResponseDTO;
import br.com.conductor.desafio.exception.ApplicationException;

public interface ContaService {

	ResponseDTO abrirConta(String cpf, String numeroConta, BigDecimal valorInicial) throws ApplicationException;

	ResponseDTO desativarConta(String numeroConta) throws ApplicationException;

	ConsultarSaldoResponseDTO consultarSaldo(String numeroConta) throws ApplicationException;

	ResponseDTO transferirValor(String cpfOrigem, String cpfDestino, String contaOrigem, String contaDestino,
			BigDecimal valor) throws ApplicationException;

	ExtratoOperacaoResponseDTO extratoOperacao(String cpf, String numeroConta) throws ApplicationException;

	ResponseDTO adicionarTransacao(String cpf, String numeroConta, Integer tipoOperacao, BigDecimal valor,
			String observacao) throws ApplicationException;
}
