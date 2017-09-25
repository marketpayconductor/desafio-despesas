package br.com.conductor.desafiodespesas.service;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conductor.desafiodespesas.componente.ParserApi;
import br.com.conductor.desafiodespesas.domain.conta.Conta;
import br.com.conductor.desafiodespesas.domain.conta.ContaRepository;
import br.com.conductor.desafiodespesas.domain.movimentacao.Movimentacao;
import br.com.conductor.desafiodespesas.domain.movimentacao.MovimentacaoRepository;
import br.com.conductor.desafiodespesas.dto.ResponseDefaultApiDTO;
import br.com.conductor.desafiodespesas.enums.Codigos;
import br.com.conductor.desafiodespesas.exception.ApplicationException;
import br.com.conductor.desafiodespesas.util.Constantes;


@Service
public class DesafioService {
	
	@Autowired
	private  ContaRepository contaRepository;
	
	@Autowired	
	private MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	private ParserApi parserApi;
	
	private Logger logger = Logger.getLogger(DesafioService.class);


	public ResponseDefaultApiDTO adicionarReceitaDespesas(Conta conta, int codigoOperacao, BigDecimal valor, String observacao) throws ApplicationException {
		
		 Movimentacao movimentacao = parserApi.parserMovimentacao(codigoOperacao, valor, observacao);
		 ResponseDefaultApiDTO response  = null;
		 
		 try {
			 
			if(Constantes.CODIGO_OPERACAO_DESPESA == codigoOperacao) {				
					conta.setSaldoConta(conta.getSaldoConta().subtract(valor));
					conta.getListaDeMovimentacoes().add(movimentacao);
					movimentacaoRepository.save(movimentacao);
					contaRepository.save(conta);
					return new ResponseDefaultApiDTO(Codigos.CODIGO_SUCESSO.getCodigo(),Codigos.CODIGO_SUCESSO.getMensagem());
				
			} else if(Constantes.CODIGO_OPERACAO_RECEITA == codigoOperacao) {
					conta.setSaldoConta(conta.getSaldoConta().add(valor));
					conta.getListaDeMovimentacoes().add(movimentacao);
					movimentacaoRepository.save(movimentacao);
					contaRepository.save(conta);
					return new ResponseDefaultApiDTO(Codigos.CODIGO_SUCESSO.getCodigo(),Codigos.CODIGO_SUCESSO.getMensagem());				
			}

		}  catch (Exception e) {	
			logger.error(e);
			throw e;
		}
		return response;		
	}
	
	public ResponseDefaultApiDTO consultaSaldoConta(Conta conta) throws ApplicationException {
		
		
		ResponseDefaultApiDTO response = null;
		
		try {	
			
			response =  new ResponseDefaultApiDTO(Codigos.CODIGO_SUCESSO.getCodigo(),Codigos.CODIGO_SUCESSO.getMensagem());	
			response.setSaldoAtual(conta.getSaldoConta());
			
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		
		return response; 
		 
	}
	
	public ResponseDefaultApiDTO consultaMovimentacao(Conta conta) throws ApplicationException {
		
		
		ResponseDefaultApiDTO response = null;
	
		try {	
			
			response =  new ResponseDefaultApiDTO(Codigos.CODIGO_SUCESSO.getCodigo(),Codigos.CODIGO_SUCESSO.getMensagem());	
			response.setLista(conta.getListaDeMovimentacoes());
			response.setSaldoAtual(conta.getSaldoConta());
			
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		
		return response; 			
		 
	}
	
	public ResponseDefaultApiDTO transferir(Conta contaAtransfefir, Conta contaAreceber, BigDecimal valorTransferencia) throws ApplicationException {
		
		
		ResponseDefaultApiDTO response = null;	
		
		try {	
			
			if(contaAreceber.getSaldoConta().compareTo(valorTransferencia) > 0 ){
				contaAtransfefir.setSaldoConta(contaAtransfefir.getSaldoConta().subtract(valorTransferencia));
				contaAreceber.setSaldoConta(contaAreceber.getSaldoConta().add(valorTransferencia));
				Movimentacao movimentacao1 = parserApi.parserMovimentacao(Constantes.CODIGO_OPERACAO_TRANSFERENCIA, 
						valorTransferencia, "Realização de transferencia");
				
				Movimentacao movimentacao2 = parserApi.parserMovimentacao(Constantes.CODIGO_OPERACAO_TRANSFERENCIA, 
						valorTransferencia, "Recebimento de uma de transferencia da conta:" + contaAtransfefir.getNumeroConta());		
				
				contaAtransfefir.getListaDeMovimentacoes().add(movimentacao1);				
				contaAreceber.getListaDeMovimentacoes().add(movimentacao2);
				movimentacaoRepository.save(movimentacao1);
				movimentacaoRepository.save(movimentacao2);
				contaRepository.save(contaAreceber);
				contaRepository.save(contaAtransfefir);

				response =  new ResponseDefaultApiDTO(Codigos.CODIGO_SUCESSO.getCodigo(),Codigos.CODIGO_SUCESSO.getMensagem());	
			} else {
				throw new ApplicationException(Codigos.SALDO_INSUFICIENTE.getCodigo(), Codigos.SALDO_INSUFICIENTE.getMensagem());

			}
			
		} catch (ApplicationException e) {
			logger.error(e);
			throw e;
			
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		
		return response; 
		
		 
	}
	
	public ResponseDefaultApiDTO cadastrarConta(String numeroConta, String cpfCliente, BigDecimal saldo) {
		
		ResponseDefaultApiDTO response = null;
		Conta conta = null;
		
		try {	
			
			conta = new ParserApi().parserConta(numeroConta, cpfCliente, saldo);
			response =  new ResponseDefaultApiDTO(Codigos.CODIGO_SUCESSO.getCodigo(),Codigos.CODIGO_SUCESSO.getMensagem());		
			contaRepository.save(conta);			
			
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		
		return response; 
	}
	
	
	
}
