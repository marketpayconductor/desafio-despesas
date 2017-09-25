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

import br.com.conductor.selecao.dto.CancelarReceitaDTO;
import br.com.conductor.selecao.dto.PagarReceitaDTO;
import br.com.conductor.selecao.dto.SalvarAlterarReceitaDTO;
import br.com.conductor.selecao.exception.EntidadeNaoEncontrada;
import br.com.conductor.selecao.exception.ParametroRequestInvalidoException;
import br.com.conductor.selecao.exception.ReceitaNaoAbertaException;
import br.com.conductor.selecao.model.Receita;
import br.com.conductor.selecao.service.ReceitaService;
import br.com.conductor.selecao.util.ApiErrorMessage;

@RestController
@RequestMapping("/api/receita")
public class ReceitaController {

	public static final Logger logger = LoggerFactory.getLogger(ReceitaController.class);

	@Autowired
	ReceitaService receitaService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Receita>> listarReceitas() {
		logger.info("Chamando o metodo: ReceitaController.listarReceitas()");
		List<Receita> receitas = receitaService.listarReceitas();
		if (receitas.isEmpty()) {
			logger.error("N\u00E3o existem despesas cadastradas");
			return new ResponseEntity<List<Receita>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Receita>>(receitas, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> pesquisarReceita(@PathVariable("id") long id) {
		logger.info("Chamando o metodo: ReceitaController.pesquisarConta()");
		Receita receita = receitaService.pesquisarReceitaPorId(id);
		if (receita == null) {
			logger.error("Receita n\u00E3o encontrada");
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage("Receita n\u00E3o encontrada"),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Receita>(receita, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> salvarReceita(@RequestBody SalvarAlterarReceitaDTO receitaDTO,
			UriComponentsBuilder ucBuilder) {
		logger.info("Chamando o metodo: ReceitaController.salvarReceita()");
		Receita receita = null;
		try {
			receita = receitaService.salvarReceita(receitaDTO);
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
		headers.setLocation(ucBuilder.path("/api/receita/{id}").buildAndExpand(receita.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> alterarReceita(@PathVariable("id") long id,
			@RequestBody SalvarAlterarReceitaDTO receitaDTO) {
		logger.info("Chamando o metodo: ReceitaController.alterarReceita()");
		Receita receita = null;
		try {
			receitaService.alterarReceita(id, receitaDTO);
		} catch (ParametroRequestInvalidoException e) {
			logger.error(e.getMensagemParametroInvalido());
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage(e.getMensagemParametroInvalido()),
					HttpStatus.BAD_REQUEST);
		} catch (ReceitaNaoAbertaException e) {
			logger.error("S\u00F3 \u00E9 poss\u00EDvel alterar receitas em aberto");
			return new ResponseEntity<ApiErrorMessage>(
					new ApiErrorMessage("S\u00F3 \u00E9 poss\u00EDvel alterar receitas em aberto"),
					HttpStatus.BAD_REQUEST);
		} catch (EntidadeNaoEncontrada e) {
			logger.error(e.getMensagemEntidadeNaoEncontrada());
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage(e.getMensagemEntidadeNaoEncontrada()),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Receita>(receita, HttpStatus.OK);
	}

	@RequestMapping(value = "/receber/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> receberReceita(@PathVariable("id") long id, @RequestBody PagarReceitaDTO receitaDTO) {
		logger.info("Chamando o metodo: ReceitaController.receberReceita()");
		Receita receita = null;
		try {
			receita = receitaService.pagarReceita(id, receitaDTO);
		} catch (EntidadeNaoEncontrada e) {
			logger.error(e.getMensagemEntidadeNaoEncontrada());
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage(e.getMensagemEntidadeNaoEncontrada()),
					HttpStatus.NOT_FOUND);
		} catch (ReceitaNaoAbertaException e) {
			logger.error("S\u00F3 \u00E9 poss\u00EDvel receber receitas em aberto");
			return new ResponseEntity<ApiErrorMessage>(
					new ApiErrorMessage("S\u00F3 \u00E9 poss\u00EDvel receber receitas em aberto"),
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Receita>(receita, HttpStatus.OK);
	}

	@RequestMapping(value = "/cancelar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> cancelarReceita(@PathVariable("id") long id,
			@RequestBody CancelarReceitaDTO cancelarReceitaDTO, UriComponentsBuilder ucBuilder) {
		logger.info("Chamando o metodo: ReceitaController.cancelarReceita()");
		try {
			receitaService.cancelarReceita(id, cancelarReceitaDTO);
		} catch (EntidadeNaoEncontrada e) {
			logger.error(e.getMensagemEntidadeNaoEncontrada());
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage(e.getMensagemEntidadeNaoEncontrada()),
					HttpStatus.NOT_FOUND);
		} catch (ReceitaNaoAbertaException e) {
			logger.error("S\u00F3 \u00E9 poss\u00EDvel cancelar receitas em aberto");
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage("Receita ja foi recebida"),
					HttpStatus.BAD_REQUEST);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/receita/{id}").buildAndExpand(id).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

}