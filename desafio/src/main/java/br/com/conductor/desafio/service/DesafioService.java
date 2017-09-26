package br.com.conductor.desafio.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conductor.desafio.exception.ContaInexistenteException;
import br.com.conductor.desafio.exception.ContaNaoPossuiFundosException;
import br.com.conductor.desafio.exception.NumeroContaDuplicadoException;
import br.com.conductor.desafio.exception.ValorLancamentoException;
import br.com.conductor.desafio.model.Conta;
import br.com.conductor.desafio.model.Lancamento;
import br.com.conductor.desafio.repository.ContaRepository;
import br.com.conductor.desafio.repository.LancamentoRepository;
import br.com.conductor.desafio.util.TiposLancamento;

@Service
public class DesafioService {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public List<Conta> listarContas(){
		
		return this.contaRepository.findAll();
		
	}
	
	public Conta obterConta(Integer idConta) throws Exception{
		
		Conta conta = this.contaRepository.findByIdConta(idConta);
		
		if(conta == null) {
			throw new ContaInexistenteException();
		}
		
		return conta;

	}
	
	public Conta criarConta(Conta conta) throws Exception {
		
		if(this.contaRepository.findByNumeroConta(conta.getNumeroConta()) != null){
			throw new NumeroContaDuplicadoException();
		}

		return this.contaRepository.save(conta);

	}
	
	public void deletarConta(Integer id) throws Exception{
		
		Conta conta = obterConta(id);
		
		if(conta != null) {
			this.contaRepository.delete(conta);
		}
	}
	
	public List<Lancamento> listarLancamentosConta(Integer idConta) throws Exception {
		
		Conta conta = obterConta(idConta);
		
		return this.lancamentoRepository.findByConta(conta);
		
	}
	
	public Lancamento adicionarLancamento(Lancamento lancamento) throws Exception {
		
		BigDecimal valorLancamento = lancamento.getValor();
		
		if(valorLancamento.compareTo(BigDecimal.ZERO) != 1){
			throw new ValorLancamentoException();
		}
		
		if(lancamento.getTipoLancamento().getTipo().equals("SAÍDA")){
			valorLancamento = valorLancamento.multiply(new BigDecimal(-1));
		}
		
		Conta conta = lancamento.getConta();
		conta.setSaldo(conta.getSaldo().add(valorLancamento));
		
		this.contaRepository.save(conta);
		
		return this.lancamentoRepository.save(lancamento);

	}
	
	public void transferir(Integer idContaOrigem, Integer idContaDestino,
			Lancamento lancamento) throws Exception {

		Conta contaOrigem = obterConta(idContaOrigem);
		Conta contaDestino = obterConta(idContaDestino);
		
		BigDecimal valorLancamento = lancamento.getValor();

		if(valorLancamento.compareTo(BigDecimal.ZERO) != 1){
			throw new ValorLancamentoException();
		}
		
		if(valorLancamento.compareTo(contaOrigem.getSaldo()) == 1){
			throw new ContaNaoPossuiFundosException();
		}
		
		contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valorLancamento));
		this.contaRepository.save(contaOrigem);
		
		contaDestino.setSaldo(contaDestino.getSaldo().add(valorLancamento));
		this.contaRepository.save(contaDestino);
		
		lancamento.setData(new Date());
		lancamento.setTipoLancamento(TiposLancamento.TRANSFERENCIA_SAIDA);
		lancamento.setConta(contaOrigem);
		this.lancamentoRepository.save(lancamento);
		
		Lancamento lancamentoDestino = new Lancamento();
		lancamentoDestino.setDescricao(lancamento.getDescricao());
		lancamentoDestino.setValor(lancamento.getValor());
		lancamentoDestino.setData(lancamento.getData());
		lancamentoDestino.setTipoLancamento(TiposLancamento.TRANSFERENCIA_ENTRADA);
		lancamentoDestino.setConta(contaDestino);
		this.lancamentoRepository.save(lancamentoDestino);

	}

}
