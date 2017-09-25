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

import br.com.conductor.selecao.model.TipoDespesa;
import br.com.conductor.selecao.service.TipoDespesaService;
import br.com.conductor.selecao.util.ApiErrorMessage;

@RestController
@RequestMapping("/api/tipodespesa")
public class TipoDespesaController {

	public static final Logger logger = LoggerFactory.getLogger(TipoDespesaController.class);

	@Autowired
	TipoDespesaService tipoDespesaService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<TipoDespesa>> listarTiposReceitas() {
		logger.info("Chamando o metodo: TipoDespesaController.listarTiposReceitas()");
		List<TipoDespesa> tipoDespesas = tipoDespesaService.listarTipoDespesas();
		if (tipoDespesas.isEmpty()) {
			logger.error("N\u00E3o existem tipos de despesas cadastradas");
			return new ResponseEntity<List<TipoDespesa>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<TipoDespesa>>(tipoDespesas, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> pesquisarTipoDespesa(@PathVariable("id") long id) {
		logger.info("Chamando o metodo: TipoDespesaController.pesquisarTipoDespesa()");
		TipoDespesa tipoDespesa = tipoDespesaService.pesquisarTipoDespesaPorId(id);
		if (tipoDespesa == null) {
			logger.error("Tipo Despesa n\u00E3o encontrada");
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage("Tipo Receita n\u00E3o encontrada"),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<TipoDespesa>(tipoDespesa, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> salvarTipoDespesa(@RequestBody TipoDespesa tipoDespesa, UriComponentsBuilder ucBuilder) {
		logger.info("Chamando o metodo: TipoDespesaController.salvarTipoDespesa()");
		if (tipoDespesaService.verificarTipoDespesaExiste(tipoDespesa)) {
			logger.error("Tipo Despesa j\u00E1 cadastrada");
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage("Tipo Despesa j\u00E1 cadastrada"),
					HttpStatus.CONFLICT);
		}
		tipoDespesaService.salvarTipoDespesa(tipoDespesa);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/tipodespesa/{id}").buildAndExpand(tipoDespesa.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> alterarTipoDespesa(@PathVariable("id") long id,
			@RequestBody TipoDespesa tipoDespesaParam) {
		logger.info("Chamando o metodo: TipoDespesaController.alterarTipoDespesa()");
		TipoDespesa tipoDespesa = tipoDespesaService.pesquisarTipoDespesaPorId(id);

		if (tipoDespesa == null) {
			logger.error("Tipo Despesa n\u00E3o encontrada");
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage("Tipo Despesa n\u00E3o encontrada"),
					HttpStatus.NOT_FOUND);
		}

		tipoDespesa.setDescricao(tipoDespesaParam.getDescricao());

		tipoDespesaService.alterarTipoDespesa(tipoDespesa);
		return new ResponseEntity<TipoDespesa>(tipoDespesa, HttpStatus.OK);
	}

}