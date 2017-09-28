package br.com.conductor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.conductor.bean.Conta;
import br.com.conductor.dto.RequestDTO;
import br.com.conductor.dto.ResponseDTO;
import br.com.conductor.service.ContaService;

@RestController
@RequestMapping(path="/conta")
public class ContaController {
	
	
	@Autowired
	private ContaService service;
	
	@RequestMapping(method = RequestMethod.PUT, value = "/adicionarDespesa")
	public ResponseEntity<ResponseDTO> adicionarDespesa(@RequestBody RequestDTO request) {
		ResponseDTO response = service.adicionarDespesa(request.getValor(), request.getNumeroConta());
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/adicionarReceita")
	public ResponseEntity<ResponseDTO> adicionarReceita(@RequestBody RequestDTO request) {
		ResponseDTO response = service.adicionarReceita(request.getValor(), request.getNumeroConta());
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/transferirCredito")
	public ResponseEntity<ResponseDTO> transferirCredito(@RequestBody RequestDTO request) {
		ResponseDTO response = service.transferirCredito(request.getNumeroContaDebitar(), 
				request.getNumeroContaCreditar(), request.getValor());
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/consultarSaldoAtual/{numeroConta}")
	public ResponseEntity<ResponseDTO> consultarSaldoAtual(@PathVariable("numeroConta") Long numeroConta) {
		ResponseDTO response = service.consultarSaldoAtual(numeroConta);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/cadastrarConta")
	public ResponseEntity<ResponseDTO> cadastrarConta(@RequestBody Conta conta) {
		ResponseDTO response = service.cadastrarConta(conta);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.CREATED);
	}
	
}
