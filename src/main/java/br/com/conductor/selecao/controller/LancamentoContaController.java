package br.com.conductor.selecao.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.conductor.selecao.model.LancamentoConta;
import br.com.conductor.selecao.service.LancamentoContaService;
import br.com.conductor.selecao.util.ApiErrorMessage;

@RestController
@RequestMapping("/api/lancamentoconta")
public class LancamentoContaController {

	public static final Logger logger = LoggerFactory.getLogger(LancamentoContaController.class);

	@Autowired
	LancamentoContaService lancamentoContaService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<LancamentoConta>> listarLancamentoContas() {
		logger.info("Chamando o metodo: LancamentoContaController.listarLancamentoContas()");
		List<LancamentoConta> lancamentoContas = lancamentoContaService.listarLancamentoContas();
		if (lancamentoContas.isEmpty()) {
			logger.error("N\u00E3o existem lan\00C7amentos de contas cadastradas");
			return new ResponseEntity<List<LancamentoConta>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<LancamentoConta>>(lancamentoContas, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> pesquisarLancamentoConta(@PathVariable("id") long id) {
		logger.info("Chamando o metodo: LancamentoContaController.pesquisarLancamentoConta()");
		LancamentoConta lancamentoConta = lancamentoContaService.pesquisarLancamentoContaPorId(id);
		if (lancamentoConta == null) {
			logger.error("Lancamento Conta n\u00E3o encontrado");
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage("Lancamento Conta n\u00E3o encontrado"),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<LancamentoConta>(lancamentoConta, HttpStatus.OK);
	}

}