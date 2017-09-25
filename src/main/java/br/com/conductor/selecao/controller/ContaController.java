package br.com.conductor.selecao.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.conductor.selecao.dto.SalvarAlterarContaDTO;
import br.com.conductor.selecao.model.Conta;
import br.com.conductor.selecao.service.ContaService;
import br.com.conductor.selecao.util.ApiErrorMessage;

@RestController
@RequestMapping("/api/conta")
public class ContaController {

	public static final Logger logger = LoggerFactory.getLogger(ContaController.class);

	@Autowired
	ContaService contaService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Conta>> listarContas() {
		logger.info("Chamando o metodo: ContaController.listarContas()");
		List<Conta> contas = contaService.listarContas();		
		if (contas.isEmpty()) {
			logger.error("N\u00E3o existem contas cadastradas");
			return new ResponseEntity<List<Conta>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Conta>>(contas, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> pesquisarConta(@PathVariable("id") long id) {
		logger.info("Chamando o metodo: ContaController.pesquisarConta()");
		Conta conta = contaService.pesquisarContaPorId(id);
		if (conta == null) {
			logger.error("Conta n\u00E3o encontrada");
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage("Conta n\u00E3o encontrada"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Conta>(conta, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> salvarConta(@RequestBody SalvarAlterarContaDTO contaParam, UriComponentsBuilder ucBuilder) {
		logger.info("Chamando o metodo: ContaController.salvarConta()");
		
		Conta conta = new Conta();
		conta.setAgencia(contaParam.getAgencia());
		conta.setBanco(contaParam.getBanco());
		conta.setConta(contaParam.getConta());
		conta.setDescricao(contaParam.getDescricao());
		
		if (contaService.verificarContaExiste(conta)) {
			logger.error("Conta j\u00E1 cadastrada");
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage("Conta j\u00E1 cadastrada"),HttpStatus.CONFLICT);
		}
		
		contaService.salvarConta(conta);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/conta/{id}").buildAndExpand(conta.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> alterarConta(@PathVariable("id") long id, @RequestBody SalvarAlterarContaDTO contaParam) {
		logger.info("Chamando o metodo: ContaController.alterarConta()");
		Conta conta = contaService.pesquisarContaPorId(id);

		if (conta == null) {
			logger.error("Conta n\u00E3o encontrada");
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage("Conta n\u00E3o encontrada"),
					HttpStatus.NOT_FOUND);
		}

		conta.setAgencia(contaParam.getAgencia());
		conta.setBanco(contaParam.getBanco());
		conta.setConta(contaParam.getConta());
		conta.setDescricao(contaParam.getDescricao());

		contaService.alterarConta(conta);
		return new ResponseEntity<Conta>(conta, HttpStatus.OK);
	}

}