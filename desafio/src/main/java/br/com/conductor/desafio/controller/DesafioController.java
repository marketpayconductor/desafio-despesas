package br.com.conductor.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.conductor.desafio.exception.ContaInexistenteException;
import br.com.conductor.desafio.exception.ContaNaoPossuiFundosException;
import br.com.conductor.desafio.exception.DesafioException;
import br.com.conductor.desafio.exception.NumeroContaDuplicadoException;
import br.com.conductor.desafio.exception.ValorLancamentoException;
import br.com.conductor.desafio.model.Conta;
import br.com.conductor.desafio.model.Lancamento;
import br.com.conductor.desafio.service.DesafioService;
import br.com.conductor.desafio.util.RespostaDesafio;

@RestController
@RequestMapping(value="/")
public class DesafioController {
	
	@Autowired
	private DesafioService desafioService;
	
	@RequestMapping(value="conta", method=RequestMethod.GET)
	public ResponseEntity<RespostaDesafio> listarContas() {
		
		List<Conta> contas = this.desafioService.listarContas();
		
		if(contas.isEmpty()){
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(HttpStatus.NO_CONTENT, "Nenhuma conta cadastrada", null), 
					HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<RespostaDesafio>(
				new RespostaDesafio(HttpStatus.OK, HttpStatus.OK.name(), contas), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value="conta/{id}", method=RequestMethod.GET)
	public ResponseEntity<RespostaDesafio> obterConta(@PathVariable("id") int id) {
		
		Conta conta = null; 
		try{
			
			conta = this.desafioService.obterConta(id);
			
		}catch(ContaInexistenteException e){
			e.printStackTrace();
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(HttpStatus.OK, e.getMessage(), null), 
					HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<RespostaDesafio>(
				new RespostaDesafio(HttpStatus.OK, HttpStatus.OK.name(), conta), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value="conta", method=RequestMethod.POST)
	public ResponseEntity<RespostaDesafio> criarConta(@RequestBody Conta conta) {
		
		Conta contaCriada = null;
		
		try {
			
			contaCriada = this.desafioService.criarConta(conta);
		
		} catch (NumeroContaDuplicadoException e){
			e.printStackTrace();
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(HttpStatus.BAD_REQUEST, e.getMessage(), null), 
					HttpStatus.BAD_REQUEST);
			
		} catch (DataIntegrityViolationException e){
			e.printStackTrace();
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(HttpStatus.BAD_REQUEST, "Parâmetro Ausente", null), 
					HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<RespostaDesafio>(
				new RespostaDesafio(HttpStatus.OK, HttpStatus.OK.name(), contaCriada), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value="conta/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<RespostaDesafio> deletarConta(@PathVariable("id") int id) {
		
		try {

			this.desafioService.deletarConta(id);

		}catch(ContaInexistenteException e){
			e.printStackTrace();
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(HttpStatus.NOT_FOUND, e.getMessage(), null), 
					HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<RespostaDesafio>(
				new RespostaDesafio(HttpStatus.OK, HttpStatus.OK.name(), null), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value="conta/{idConta}/lancamento", method=RequestMethod.GET)
	public ResponseEntity<RespostaDesafio> listarLancamentos(@PathVariable("idConta") int idConta) {
		
		List<Lancamento> lancamentos = null;
		
		try {
			
			lancamentos = this.desafioService.listarLancamentosConta(idConta);
			
		} catch (ContaInexistenteException e) {
			e.printStackTrace();
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(HttpStatus.NOT_FOUND, e.getMessage(), null), 
					HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(lancamentos == null || lancamentos.isEmpty()){
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(HttpStatus.OK, "Nenhum Lançamento para a conta", null), 
					HttpStatus.OK);
		}
		
		return new ResponseEntity<RespostaDesafio>(
				new RespostaDesafio(HttpStatus.OK, HttpStatus.OK.name(), lancamentos), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value="conta/{idConta}/lancamento", method=RequestMethod.POST)
	public ResponseEntity<RespostaDesafio> adicionarLancamento(@PathVariable("idConta") int idConta, 
			@RequestBody Lancamento lancamento) {
		
		Conta conta = null;
		
		try {
			
			if(!ehLancamentoValido(lancamento)) {
				return new ResponseEntity<RespostaDesafio>(
						new RespostaDesafio(HttpStatus.BAD_REQUEST, "Os Tipos de Lançamento tem que ser 0 (Receita) ou 1 (Despesa)", null), 
						HttpStatus.BAD_REQUEST);
			}
			
			conta = this.desafioService.obterConta(idConta);
			
			lancamento.setConta(conta);
			
			this.desafioService.adicionarLancamento(lancamento);

		
		} catch(DesafioException e){	
			e.printStackTrace();
			HttpStatus status = null;
			
			if(e instanceof ContaInexistenteException){
				status = HttpStatus.NOT_FOUND;
			} else if(e instanceof ValorLancamentoException){
				status = HttpStatus.BAD_REQUEST;
			}
			
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(status, e.getMessage(), null), 
					status);
			
		} catch (DataIntegrityViolationException e){
			e.printStackTrace();
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(HttpStatus.BAD_REQUEST, "Parâmetro Ausente", null), 
					HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<RespostaDesafio>(
				new RespostaDesafio(HttpStatus.OK, HttpStatus.OK.name(), null), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value="conta/{idContaOrigem}/transferir/{idContaDestino}", method=RequestMethod.POST)
	public ResponseEntity<RespostaDesafio> trasferir(@PathVariable("idContaOrigem") int idContaOrigem, 
			@PathVariable("idContaDestino") int idContaDestino,
			@RequestBody Lancamento lancamento) {
		
		try {
			
			this.desafioService.transferir(idContaOrigem, idContaDestino, lancamento);
		
		} catch(DesafioException e){	
			e.printStackTrace();
			HttpStatus status = null;
			
			if(e instanceof ContaInexistenteException){
				status = HttpStatus.NOT_FOUND;
			} else if(e instanceof ValorLancamentoException
					|| e instanceof ContaNaoPossuiFundosException){
				status = HttpStatus.BAD_REQUEST;
			}
			
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(status, e.getMessage(), null), 
					status);
			
		} catch (DataIntegrityViolationException e){
			e.printStackTrace();
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(HttpStatus.BAD_REQUEST, "Parâmetro Ausente", null), 
					HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<RespostaDesafio>(
				new RespostaDesafio(HttpStatus.OK, HttpStatus.OK.name(), null), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value="conta/{id}/consultarSaldo", method=RequestMethod.GET)
	public ResponseEntity<RespostaDesafio> consultarSaldo(@PathVariable("id") int id) {
		
		Conta conta = null;
		
		try{
			
			conta = this.desafioService.obterConta(id);
			
		}catch(ContaInexistenteException e){
			e.printStackTrace();
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(HttpStatus.NOT_FOUND, e.getMessage(), null), 
					HttpStatus.NOT_FOUND);
			
		} catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<RespostaDesafio>(
					new RespostaDesafio(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<RespostaDesafio>(
				new RespostaDesafio(HttpStatus.OK, HttpStatus.OK.name(), 
						"Saldo: " + conta.getSaldo()), 
				HttpStatus.OK);
	}
	
	

	private boolean ehLancamentoValido(Lancamento lancamento) {
		
		return lancamento.getTipoLancamento() != null &&
				(lancamento.getTipoLancamento().getNome().equals("RECEITA")
				|| lancamento.getTipoLancamento().getNome().equals("DESPESA"));
	}
	
}
