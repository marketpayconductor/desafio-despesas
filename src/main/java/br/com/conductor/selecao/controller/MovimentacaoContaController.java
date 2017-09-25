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

import br.com.conductor.selecao.dto.SalvarMovimentacaoContaDTO;
import br.com.conductor.selecao.exception.EntidadeNaoEncontrada;
import br.com.conductor.selecao.exception.ParametroRequestInvalidoException;
import br.com.conductor.selecao.model.MovimentacaoConta;
import br.com.conductor.selecao.service.MovimentacaoContaService;
import br.com.conductor.selecao.util.ApiErrorMessage;

@RestController
@RequestMapping("/api/movimentacaoconta")
public class MovimentacaoContaController {

	public static final Logger logger = LoggerFactory.getLogger(MovimentacaoContaController.class);

	@Autowired
	MovimentacaoContaService movimentacaoContaService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<MovimentacaoConta>> listarMovimentacoesContas() {
		logger.info("Chamando o metodo: MovimentacaoContaController.listarMovimentacoesContas()");
		List<MovimentacaoConta> lancamentoContas = movimentacaoContaService.listarMovimentacaoContas();
		if (lancamentoContas.isEmpty()) {
			logger.error("N\u00E3o existem movimenta\u00C7\u00F5es de contas cadastradas");
			return new ResponseEntity<List<MovimentacaoConta>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<MovimentacaoConta>>(lancamentoContas, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> pesquisarMovimentacaoConta(@PathVariable("id") long id) {
		logger.info("Chamando o metodo: MovimentacaoContaController.pesquisarMovimentacaoConta()");
		MovimentacaoConta lancamentoConta = movimentacaoContaService.pesquisarMovimentacaoContaPorId(id);
		if (lancamentoConta == null) {
			logger.error("Movimentacao Conta n\u00E3o encontrada");
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage("User with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<MovimentacaoConta>(lancamentoConta, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> salvarMovimentacaoConta(@RequestBody SalvarMovimentacaoContaDTO salvarMovimentacaoContaDTO,
			UriComponentsBuilder ucBuilder) {
		logger.info("Chamando o metodo: MovimentacaoContaController.salvarMovimentacaoConta()");
		MovimentacaoConta movimentacaoConta = null;
		try {
			movimentacaoConta = movimentacaoContaService.salvarMovimentacaoConta(salvarMovimentacaoContaDTO);
		} catch (EntidadeNaoEncontrada e) {
			logger.error(e.getMensagemEntidadeNaoEncontrada());
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage(e.getMensagemEntidadeNaoEncontrada()),
					HttpStatus.NOT_FOUND);
		} catch (ParametroRequestInvalidoException e) {
			logger.error(e.getMensagemParametroInvalido());
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage(e.getMensagemParametroInvalido()),
					HttpStatus.BAD_REQUEST);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("/api/movimentacaoconta/{id}").buildAndExpand(movimentacaoConta.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

}