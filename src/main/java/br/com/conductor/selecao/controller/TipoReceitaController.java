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

import br.com.conductor.selecao.model.TipoReceita;
import br.com.conductor.selecao.service.TipoReceitaService;
import br.com.conductor.selecao.util.ApiErrorMessage;

@RestController
@RequestMapping("/api/tiporeceita")
public class TipoReceitaController {

	public static final Logger logger = LoggerFactory.getLogger(TipoReceitaController.class);

	@Autowired
	TipoReceitaService tipoReceitaService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<TipoReceita>> listarTiposReceitas() {
		logger.info("Chamando o metodo: TipoReceitaController.listarTiposReceitas()");
		List<TipoReceita> tipoReceitas = tipoReceitaService.listarTipoReceitas();		
		if (tipoReceitas.isEmpty()) {
			logger.error("N\u00E3o existem tipos de receitas cadastradas");
			return new ResponseEntity<List<TipoReceita>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<TipoReceita>>(tipoReceitas, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> pesquisarTipoReceita(@PathVariable("id") long id) {
		logger.info("Chamando o metodo: TipoReceitaController.pesquisarTipoReceita()");
		TipoReceita tipoReceita = tipoReceitaService.pesquisarTipoReceitaPorId(id);
		if (tipoReceita == null) {
			logger.error("Tipo Receita n\u00E3o encontrada");
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage("Tipo Receita n\u00E3o encontrada"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<TipoReceita>(tipoReceita, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> salvarTipoReceita(@RequestBody TipoReceita tipoReceita, UriComponentsBuilder ucBuilder) {
		logger.info("Chamando o metodo: TipoReceitaController.salvarTipoReceita()");
		if (tipoReceitaService.verificarTipoReceitaExiste(tipoReceita)) {
			logger.error("Tipo Despesa j\u00E1 cadastrada");
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage("Tipo Despesa j\u00E1 cadastrada"),HttpStatus.CONFLICT);
		}
		tipoReceitaService.salvarTipoReceita(tipoReceita);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/tiporeceita/{id}").buildAndExpand(tipoReceita.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> alterarTipoReceita(@PathVariable("id") long id, @RequestBody TipoReceita tipoReceitaParam) {
		logger.info("Chamando o metodo: TipoReceitaController.alterarTipoReceita()");
		TipoReceita tipoReceita = tipoReceitaService.pesquisarTipoReceitaPorId(id);

		if (tipoReceita == null) {
			logger.error("Tipo Receita n\u00E3o encontrada");
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage("Tipo Receita n\u00E3o encontrada"),
					HttpStatus.NOT_FOUND);
		}

		tipoReceita.setDescricao(tipoReceitaParam.getDescricao());		

		tipoReceitaService.alterarTipoReceita(tipoReceita);
		return new ResponseEntity<TipoReceita>(tipoReceita, HttpStatus.OK);
	}

}