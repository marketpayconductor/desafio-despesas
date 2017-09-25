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

import br.com.conductor.selecao.dto.CancelarDespesaDTO;
import br.com.conductor.selecao.dto.PagarDespesaDTO;
import br.com.conductor.selecao.dto.SalvarAlterarDespesaDTO;
import br.com.conductor.selecao.exception.DespesaNaoAbertaException;
import br.com.conductor.selecao.exception.EntidadeNaoEncontrada;
import br.com.conductor.selecao.exception.ParametroRequestInvalidoException;
import br.com.conductor.selecao.model.Despesa;
import br.com.conductor.selecao.service.DespesaService;
import br.com.conductor.selecao.util.ApiErrorMessage;

@RestController
@RequestMapping("/api/despesa")
public class DespesaController {

	public static final Logger logger = LoggerFactory.getLogger(DespesaController.class);

	@Autowired
	DespesaService despesaService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Despesa>> listarDespesas() {
		logger.info("Chamando o metodo: DespesaController.listarDespesas()");
		List<Despesa> despesas = despesaService.listarDespesas();
		if (despesas.isEmpty()) {
			logger.error("N\u00E3o existem despesas cadastradas");
			return new ResponseEntity<List<Despesa>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Despesa>>(despesas, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> pesquisarDespesa(@PathVariable("id") long id) {
		logger.info("Chamando o metodo: DespesaController.pesquisarDespesa()");
		Despesa despesa = despesaService.pesquisarDespesaPorId(id);
		if (despesa == null) {
			logger.error("Despesa n\u00E3o encontrada");
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage("Despesa n\u00E3o encontrada"),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Despesa>(despesa, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> salvarDespesa(@RequestBody SalvarAlterarDespesaDTO despesaDTO,
			UriComponentsBuilder ucBuilder) {
		logger.info("Chamando o metodo: DespesaController.salvarDespesa()");
		Despesa despesa = null;
		try {
			despesa = despesaService.salvarDespesa(despesaDTO);
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
		headers.setLocation(ucBuilder.path("/api/despesa/{id}").buildAndExpand(despesa.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> alterarDespesa(@PathVariable("id") long id,
			@RequestBody SalvarAlterarDespesaDTO despesaDTO) {
		logger.info("Chamando o metodo: DespesaController.alterarDespesa()");
		Despesa despesa = null;
		try {
			despesaService.alterarDespesa(id, despesaDTO);
		} catch (ParametroRequestInvalidoException e) {
			logger.error(e.getMensagemParametroInvalido());
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage(e.getMensagemParametroInvalido()),
					HttpStatus.BAD_REQUEST);
		} catch (DespesaNaoAbertaException e) {
			logger.error("S\u00F3 \u00E9 poss\u00EDvel alterar despesas em aberto");
			return new ResponseEntity<ApiErrorMessage>(
					new ApiErrorMessage("S\u00F3 \u00E9 poss\u00EDvel alterar despesas em aberto"),
					HttpStatus.BAD_REQUEST);
		} catch (EntidadeNaoEncontrada e) {
			logger.error(e.getMensagemEntidadeNaoEncontrada());
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage(e.getMensagemEntidadeNaoEncontrada()),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Despesa>(despesa, HttpStatus.OK);
	}

	@RequestMapping(value = "/pagar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> pagarDespesa(@PathVariable("id") long id, @RequestBody PagarDespesaDTO despesaDTO) {
		logger.info("Chamando o metodo: DespesaController.pagarDespesa()");
		Despesa despesa = null;
		try {
			despesa = despesaService.pagarDespesa(id, despesaDTO);
		} catch (EntidadeNaoEncontrada e) {
			logger.error(e.getMensagemEntidadeNaoEncontrada());
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage(e.getMensagemEntidadeNaoEncontrada()),
					HttpStatus.NOT_FOUND);
		} catch (DespesaNaoAbertaException e) {
			logger.error("S\u00F3 \u00E9 poss\u00EDvel pagar despesas em aberto");
			return new ResponseEntity<ApiErrorMessage>(
					new ApiErrorMessage("S\u00F3 \u00E9 poss\u00EDvel pagar despesas em aberto"),
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Despesa>(despesa, HttpStatus.OK);
	}

	@RequestMapping(value = "/cancelar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> cancelarDespesa(@PathVariable("id") long id,
			@RequestBody CancelarDespesaDTO cancelarDespesaDTO, UriComponentsBuilder ucBuilder) {
		logger.info("Chamando o metodo: DespesaController.cancelarDespesaDTO()");
		try {
			despesaService.cancelarDespesa(id, cancelarDespesaDTO);
		} catch (EntidadeNaoEncontrada e) {
			logger.error(e.getMensagemEntidadeNaoEncontrada());
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage(e.getMensagemEntidadeNaoEncontrada()),
					HttpStatus.NOT_FOUND);
		} catch (DespesaNaoAbertaException e) {
			logger.error("S\u00F3 \u00E9 poss\u00EDvel cancelar despesas em aberto");
			return new ResponseEntity<ApiErrorMessage>(
					new ApiErrorMessage("S\u00F3 \u00E9 poss\u00EDvel cancelar despesas em aberto"),
					HttpStatus.BAD_REQUEST);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/despesa/{id}").buildAndExpand(id).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

}