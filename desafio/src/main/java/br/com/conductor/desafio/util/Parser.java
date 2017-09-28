package br.com.conductor.desafio.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.conductor.desafio.dto.OperacaoResponseDTO;
import br.com.conductor.desafio.model.Conta;
import br.com.conductor.desafio.model.Operacao;
import br.com.conductor.desafio.model.Usuario;

@Component
public class Parser {

	public Conta contaParser(Usuario usuario, String numeroConta, BigDecimal valorInicial) {

		Conta conta = new Conta();

		conta.setNumeroConta(numeroConta);
		conta.setSaldo(valorInicial);
		conta.setUsuario(usuario);
		conta.setDataAbertura(new Date());

		return conta;

	}

	public List<OperacaoResponseDTO> operacaoParser(List<Operacao> operacoes) {

		List<OperacaoResponseDTO> operacoesDTO = new ArrayList<>();

		for (Operacao operacao : operacoes) {
			OperacaoResponseDTO operacaoDTO = new OperacaoResponseDTO();
			operacaoDTO.setIdOperacao(operacao.getIdOperacao());
			operacaoDTO.setDataOperacao(operacao.getDataOperacao());
			operacaoDTO.setTipoOperacao(operacao.getTipoOperacao());
			operacaoDTO.setObservacao(operacao.getObservacao());
			operacaoDTO.setValorOperacao(operacao.getValorOperacao());
			operacoesDTO.add(operacaoDTO);
		}

		return operacoesDTO;
	}

}
