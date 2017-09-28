package br.com.conductor.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conductor.bean.Conta;
import br.com.conductor.bean.Lancamento;
import br.com.conductor.dto.ResponseDTO;
import br.com.conductor.repository.ContaRepository;
import br.com.conductor.repository.LancamentoRepository;
import br.com.conductor.util.ResponseEnun;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository repository;
	
	@Autowired
	private LancamentoRepository repolancamento;
	
	
	public ResponseDTO cadastrarConta(Conta conta) {
		ResponseDTO response = null;
		try {
			Conta contaDuplicada = repository.findByNumeroConta(conta.getNumeroConta());
			if (contaDuplicada == null) {
				repository.save(conta);
				response = new ResponseDTO(ResponseEnun.SUCESSO.getCodigo(), ResponseEnun.SUCESSO.getMensagem());
			} else {
				response = new ResponseDTO(ResponseEnun.CONTA_DUPLICADA.getCodigo(), ResponseEnun.CONTA_DUPLICADA.getMensagem());
			}
		} catch (Exception e) {
			response = new ResponseDTO(ResponseEnun.ERRO.getCodigo(), ResponseEnun.ERRO.getMensagem());
		}
		return response;
	}
	
	
	public ResponseDTO adicionarDespesa(BigDecimal valor, Long numeroConta){
		ResponseDTO response = null;
		
		try {
			Conta conta = repository.findByNumeroConta(numeroConta);
			if (conta != null) {
				BigDecimal valorsubtraido = conta.getSaldo().subtract(valor);
				conta.setSaldo(valorsubtraido);
				repository.save(conta);
				Lancamento lancamento = new Lancamento();
				lancamento.setDataLancamento(new Date());
				lancamento.setDescricao(ResponseEnun.DESPESA.getMensagem());
				lancamento.setValor(valor.negate());
				lancamento.setNumeroConta(conta.getNumeroConta());
				repolancamento.save(lancamento);
				response = new ResponseDTO(ResponseEnun.SUCESSO.getCodigo(), ResponseEnun.SUCESSO.getMensagem());
			} else {
				response = new ResponseDTO(ResponseEnun.CONTA_NAO_EXISTE.getCodigo(), 
						ResponseEnun.CONTA_NAO_EXISTE.getMensagem());
			}
		} catch (Exception e) {
			response = new ResponseDTO(ResponseEnun.ERRO.getCodigo(), ResponseEnun.ERRO.getMensagem());
		}
		return response;
	}
	
	public ResponseDTO adicionarReceita(BigDecimal valor, Long numeroConta){
		ResponseDTO response = null;
		try {
			Conta conta = repository.findByNumeroConta(numeroConta);
			if (conta != null) {
				BigDecimal valorAdicionado= conta.getSaldo().add(valor);
				conta.setSaldo(valorAdicionado);
				repository.save(conta);
				Lancamento lancamento = new Lancamento();
				lancamento.setDataLancamento(new Date());
				lancamento.setDescricao(ResponseEnun.RECEITA.getMensagem());
				lancamento.setValor(valor);
				lancamento.setNumeroConta(conta.getNumeroConta());
				repolancamento.save(lancamento);
				response = new ResponseDTO(ResponseEnun.SUCESSO.getCodigo(), ResponseEnun.SUCESSO.getMensagem());
			} else {
				response = new ResponseDTO(ResponseEnun.CONTA_NAO_EXISTE.getCodigo(), 
						ResponseEnun.CONTA_NAO_EXISTE.getMensagem());
			}
		} catch (Exception e) {
			response = new ResponseDTO(ResponseEnun.ERRO.getCodigo(), ResponseEnun.ERRO.getMensagem());
		}
		return response;
	}
	
	
	public ResponseDTO consultarSaldoAtual(Long numeroConta){
		ResponseDTO response = null;
		try {
			Conta conta = repository.findByNumeroConta(numeroConta);
			if (conta != null) {
				StringBuilder strBuilder = new StringBuilder();
				strBuilder.append(ResponseEnun.SALDO_ATUAL.getMensagem()).append(conta.getSaldo());
				response = new ResponseDTO(ResponseEnun.SALDO_ATUAL.getCodigo(), strBuilder.toString());
			} else {
				response = new ResponseDTO(ResponseEnun.CONTA_NAO_EXISTE.getCodigo(), 
						ResponseEnun.CONTA_NAO_EXISTE.getMensagem());
			}
		} catch (Exception e) {
			response = new ResponseDTO(ResponseEnun.ERRO.getCodigo(), ResponseEnun.ERRO.getMensagem());
		}
		return response;
	}
	
	public ResponseDTO transferirCredito(Long numeroContaDebitar, Long numeroContaCreditar, BigDecimal valor){
		ResponseDTO response = null;
		StringBuilder strBuilder = new StringBuilder();
		
		try {
			Conta contaDebitar = repository.findByNumeroConta(numeroContaDebitar);
			Conta contaCreditar = repository.findByNumeroConta(numeroContaCreditar);
			if (contaDebitar == null) {
				strBuilder.append(": ").append(numeroContaDebitar);
				response = new ResponseDTO(ResponseEnun.CONTA_NAO_EXISTE.getCodigo(), 
						ResponseEnun.CONTA_NAO_EXISTE.getMensagem().concat(strBuilder.toString()));
				return response;
			} else if (contaCreditar == null) {
				strBuilder.append(": ").append(numeroContaCreditar);
				response = new ResponseDTO(ResponseEnun.CONTA_NAO_EXISTE.getCodigo(), 
						ResponseEnun.CONTA_NAO_EXISTE.getMensagem().concat(strBuilder.toString()));
				return response;
			} else {
				BigDecimal valorDebitar = contaDebitar.getSaldo().subtract(valor);
				contaDebitar.setSaldo(valorDebitar);
				repository.save(contaDebitar);
				Lancamento lancamento = new Lancamento();
				lancamento.setDataLancamento(new Date());
				lancamento.setDescricao(ResponseEnun.TRANSFERENCIA.getMensagem());
				lancamento.setValor(valor.negate());
				lancamento.setNumeroConta(contaDebitar.getNumeroConta());
				repolancamento.save(lancamento);
				
				BigDecimal valorCreditar =  contaCreditar.getSaldo().add(valor);
				contaCreditar.setSaldo(valorCreditar);
				repository.save(contaCreditar);
				Lancamento lancamentoTransfer = new Lancamento();
				lancamentoTransfer.setDataLancamento(new Date());
				lancamentoTransfer.setDescricao(ResponseEnun.TRANSFERENCIA.getMensagem());
				lancamentoTransfer.setValor(valor);
				lancamentoTransfer.setNumeroConta(contaCreditar.getNumeroConta());
				repolancamento.save(lancamentoTransfer);
				
				response = new ResponseDTO(ResponseEnun.SUCESSO.getCodigo(), ResponseEnun.SUCESSO.getMensagem());
			}
		} catch (Exception e) {
			response = new ResponseDTO(ResponseEnun.ERRO.getCodigo(), ResponseEnun.ERRO.getMensagem());
		}
		return response;
	}
	
}
