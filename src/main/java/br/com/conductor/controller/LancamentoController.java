package br.com.conductor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.conductor.dto.ResponseLancamentoDTO;
import br.com.conductor.service.LancamentoService;

@RestController
@RequestMapping(path="/lancamento")
public class LancamentoController {
	
	@Autowired
	private LancamentoService service;
	
	@RequestMapping(method = RequestMethod.GET, value = "/historicoMovimentacao/{numeroConta}")
	public ResponseEntity<List<ResponseLancamentoDTO>> historicoMovimentacao(@PathVariable("numeroConta") Long numeroConta) {
		List<ResponseLancamentoDTO> response = service.historicoMovimentacao(numeroConta);
		return new ResponseEntity<List<ResponseLancamentoDTO>>(response, HttpStatus.OK);
	}

}
