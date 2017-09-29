package br.com.conductor.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couchbase.client.deps.io.netty.util.internal.StringUtil;

import br.com.conductor.bean.Conta;
import br.com.conductor.bean.Lancamento;
import br.com.conductor.dto.ResponseDTO;
import br.com.conductor.dto.ResponseLancamentoDTO;
import br.com.conductor.repository.ContaRepository;
import br.com.conductor.repository.LancamentoRepository;
import br.com.conductor.util.ResponseEnun;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	
	public ResponseDTO cadastrarConta(Conta conta) {
		ResponseDTO response = null;
		if (conta.getNumeroConta() != null && conta.getNomeProprietario() != null &&
				conta.getCpf() != null && conta.getSaldo() != null) {
			try {
				Conta contaDuplicada = contaRepository.findByNumeroConta(conta.getNumeroConta());
				if (contaDuplicada == null) {
					contaRepository.save(conta);
					response = new ResponseDTO(ResponseEnun.SUCESSO.getCodigo(), ResponseEnun.SUCESSO.getMensagem());
				} else {
					response = new ResponseDTO(ResponseEnun.CONTA_DUPLICADA.getCodigo(), ResponseEnun.CONTA_DUPLICADA.getMensagem());
				}
			} catch (Exception e) {
				response = new ResponseDTO(ResponseEnun.ERRO.getCodigo(), ResponseEnun.ERRO.getMensagem());
			}
		} else {
			response = new ResponseDTO(ResponseEnun.INFORMAR_CAMPO.getCodigo(), ResponseEnun.INFORMAR_CAMPO.getMensagem());
		}
		
		return response;
	}
	
	
	public ResponseDTO adicionarDespesa(BigDecimal valor, Long numeroConta){
		ResponseDTO response = null;
		if (valor != null && numeroConta != null) {
			try {
				Conta conta = contaRepository.findByNumeroConta(numeroConta);
				if (conta != null) {
					BigDecimal valorsubtraido = conta.getSaldo().subtract(valor);
					conta.setSaldo(valorsubtraido);
					contaRepository.save(conta);
					Lancamento lancamento = new Lancamento();
					lancamento.setDataLancamento(new Date());
					lancamento.setDescricao(ResponseEnun.DESPESA.getMensagem());
					lancamento.setValor(valor.negate());
					lancamento.setNumeroConta(conta.getNumeroConta());
					lancamentoRepository.save(lancamento);
					response = new ResponseDTO(ResponseEnun.SUCESSO.getCodigo(), ResponseEnun.SUCESSO.getMensagem());
				} else {
					response = new ResponseDTO(ResponseEnun.CONTA_NAO_EXISTE.getCodigo(), 
							ResponseEnun.CONTA_NAO_EXISTE.getMensagem());
				}
			} catch (Exception e) {
				response = new ResponseDTO(ResponseEnun.ERRO.getCodigo(), ResponseEnun.ERRO.getMensagem());
			}
		} else {
			response = new ResponseDTO(ResponseEnun.INFORMAR_CAMPO.getCodigo(), ResponseEnun.INFORMAR_CAMPO.getMensagem());
		}
		return response;
	}
	
	public ResponseDTO adicionarReceita(BigDecimal valor, Long numeroConta){
		ResponseDTO response = null;
		if (valor != null && numeroConta != null) {
			try {
				Conta conta = contaRepository.findByNumeroConta(numeroConta);
				if (conta != null) {
					BigDecimal valorAdicionado= conta.getSaldo().add(valor);
					conta.setSaldo(valorAdicionado);
					contaRepository.save(conta);
					Lancamento lancamento = new Lancamento();
					lancamento.setDataLancamento(new Date());
					lancamento.setDescricao(ResponseEnun.RECEITA.getMensagem());
					lancamento.setValor(valor);
					lancamento.setNumeroConta(conta.getNumeroConta());
					lancamentoRepository.save(lancamento);
					response = new ResponseDTO(ResponseEnun.SUCESSO.getCodigo(), ResponseEnun.SUCESSO.getMensagem());
				} else {
					response = new ResponseDTO(ResponseEnun.CONTA_NAO_EXISTE.getCodigo(), 
							ResponseEnun.CONTA_NAO_EXISTE.getMensagem());
				}
			} catch (Exception e) {
				response = new ResponseDTO(ResponseEnun.ERRO.getCodigo(), ResponseEnun.ERRO.getMensagem());
			}
		} else {
			response = new ResponseDTO(ResponseEnun.INFORMAR_CAMPO.getCodigo(), ResponseEnun.INFORMAR_CAMPO.getMensagem());
		}
		return response;
	}
	
	
	public ResponseDTO consultarSaldoAtual(Long numeroConta){
		ResponseDTO response = null;
		if (numeroConta != null) {
			try {
				Conta conta = contaRepository.findByNumeroConta(numeroConta);
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
		} else {
			response = new ResponseDTO(ResponseEnun.INFORMAR_CAMPO.getCodigo(), ResponseEnun.INFORMAR_CAMPO.getMensagem());
		}
		return response;
	}
	
	public ResponseDTO transferirCredito(Long numeroContaDebitar, Long numeroContaCreditar, BigDecimal valor){
		ResponseDTO response = null;
		StringBuilder strBuilder = new StringBuilder();
		if (numeroContaDebitar != null && numeroContaCreditar != null && valor != null) {
			try {
				Conta contaDebitar = contaRepository.findByNumeroConta(numeroContaDebitar);
				Conta contaCreditar = contaRepository.findByNumeroConta(numeroContaCreditar);
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
					contaRepository.save(contaDebitar);
					Lancamento lancamento = new Lancamento();
					lancamento.setDataLancamento(new Date());
					lancamento.setDescricao(ResponseEnun.TRANSFERENCIA.getMensagem());
					lancamento.setValor(valor.negate());
					lancamento.setNumeroConta(contaDebitar.getNumeroConta());
					lancamentoRepository.save(lancamento);
					
					BigDecimal valorCreditar =  contaCreditar.getSaldo().add(valor);
					contaCreditar.setSaldo(valorCreditar);
					contaRepository.save(contaCreditar);
					Lancamento lancamentoTransfer = new Lancamento();
					lancamentoTransfer.setDataLancamento(new Date());
					lancamentoTransfer.setDescricao(ResponseEnun.TRANSFERENCIA.getMensagem());
					lancamentoTransfer.setValor(valor);
					lancamentoTransfer.setNumeroConta(contaCreditar.getNumeroConta());
					lancamentoRepository.save(lancamentoTransfer);
					
					response = new ResponseDTO(ResponseEnun.SUCESSO.getCodigo(), ResponseEnun.SUCESSO.getMensagem());
				}
			} catch (Exception e) {
				response = new ResponseDTO(ResponseEnun.ERRO.getCodigo(), ResponseEnun.ERRO.getMensagem());
			}
		} else {
			response = new ResponseDTO(ResponseEnun.INFORMAR_CAMPO.getCodigo(), ResponseEnun.INFORMAR_CAMPO.getMensagem());
		}
		return response;
	}
	
	public List<ResponseLancamentoDTO> historicoMovimentacao(Long numeroConta) {
		 List<Lancamento> lancamentos = null;
		 List<ResponseLancamentoDTO> responseList = new ArrayList<>();
		 ResponseLancamentoDTO response = null;
		 if (!StringUtil.isNullOrEmpty(numeroConta.toString())) {
			 try {
					Conta conta = contaRepository.findByNumeroConta(numeroConta);
					if (conta != null) {
						lancamentos = lancamentoRepository.findByNumeroConta(numeroConta);
						if (!lancamentos.isEmpty()) {
							for (Lancamento lancamento : lancamentos) {
								ResponseLancamentoDTO responseLancamento = new ResponseLancamentoDTO();
								responseLancamento.setDataLancamento(lancamento.getDataLancamento());
								responseLancamento.setDescricao(lancamento.getDescricao());
								responseLancamento.setValor(lancamento.getValor());
								responseList.add(responseLancamento);
							}
						} else {
							response = new ResponseLancamentoDTO(ResponseEnun.SEM_LANCAMENTO.getCodigo(), 
									ResponseEnun.SEM_LANCAMENTO.getMensagem());
							responseList.add(response);
						}
					} else {
						response = new ResponseLancamentoDTO(ResponseEnun.CONTA_NAO_EXISTE.getCodigo(), 
								ResponseEnun.CONTA_NAO_EXISTE.getMensagem());
						responseList.add(response);
					}
				} catch (Exception e) {
					response = new ResponseLancamentoDTO(ResponseEnun.ERRO.getCodigo(), ResponseEnun.ERRO.getMensagem());
					responseList.add(response);
					return responseList;
				}
		 } else {
			 response = new ResponseLancamentoDTO(ResponseEnun.INFORMAR_CAMPO.getCodigo(), ResponseEnun.INFORMAR_CAMPO.getMensagem());
			 responseList.add(response);
		 }
		return responseList;
	}


	public ResponseDTO cancelarConta(Long numeroConta) {
		ResponseDTO response = null;
		if (numeroConta != null) {
			try {
				Conta conta = contaRepository.findByNumeroConta(numeroConta);
				if (conta != null) {
					contaRepository.delete(conta);
					response = new ResponseDTO(ResponseEnun.SUCESSO.getCodigo(), ResponseEnun.SUCESSO.getMensagem());
				} else {
					response = new ResponseDTO(ResponseEnun.CONTA_NAO_EXISTE.getCodigo(), ResponseEnun.CONTA_NAO_EXISTE.getMensagem());
				}
			} catch (Exception e) {
				response = new ResponseDTO(ResponseEnun.ERRO.getCodigo(), ResponseEnun.ERRO.getMensagem());
			}
		} else {
			response = new ResponseDTO(ResponseEnun.INFORMAR_CAMPO.getCodigo(), ResponseEnun.INFORMAR_CAMPO.getMensagem());
		}
		return response;
	}
	
}
