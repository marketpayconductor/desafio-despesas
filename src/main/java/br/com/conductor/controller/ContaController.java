package br.com.conductor.controller;

import java.util.List;

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
import br.com.conductor.dto.ResponseLancamentoDTO;
import br.com.conductor.service.ContaService;

@RestController
@RequestMapping(path = "/conta")
public class ContaController {

	@Autowired
	private ContaService service;

	/**
	 * @api {put} /conta/adicionarDespesa Adiciona uma despesa em uma conta informada.
	 * @apiParamExample {json} Request-Example: 
	 * { 
	 * 		"numeroConta": "107",
	 *      "valor": 100 
	 * } 
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 * 
	 * {
	 * 		"codigo": "00",
	 * 		"mensagem": "Realizado com sucesso"
	 * }
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/adicionarDespesa")
	public ResponseEntity<ResponseDTO> adicionarDespesa(@RequestBody RequestDTO request) {
		ResponseDTO response = service.adicionarDespesa(request.getValor(), request.getNumeroConta());
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	/**
	 * @api {put} /conta/adicionarReceita Adiciona uma receita em uma conta informada.
	 * @apiParamExample {json} Request-Example: 
	 * { 
	 * 		"numeroConta": "107",
	 *      "valor": 100 
	 * } 
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 * 
	 * {
	 * 		"codigo": "00",
	 * 		"mensagem": "Realizado com sucesso"
	 * }
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/adicionarReceita")
	public ResponseEntity<ResponseDTO> adicionarReceita(@RequestBody RequestDTO request) {
		ResponseDTO response = service.adicionarReceita(request.getValor(), request.getNumeroConta());
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	/**
	 * @api {put} /conta/transferirCredito Transefere um valor informado de uma conta para outra.
	 * @apiParamExample {json} Request-Example: 
	 * {
	 * 		"numeroContaDebitar":"106",
	 * 		"numeroContaCreditar":"107",
	 * 		"valor":100.50 
	 * }
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 * 
	 * {
	 * 		"codigo": "00",
	 * 		"mensagem": "Realizado com sucesso"
	 * }
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/transferirCredito")
	public ResponseEntity<ResponseDTO> transferirCredito(@RequestBody RequestDTO request) {
		ResponseDTO response = service.transferirCredito(request.getNumeroContaDebitar(),
				request.getNumeroContaCreditar(), request.getValor());
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	/**
	 * @api {get} /conta/consultarSaldoAtual/{numeroConta} Consulta o saldo atual de uma conta informada.
	 * @apiParamExample {json} Request-Example: 
	 * 
	 * http://localhost:8080/conta/consultarSaldoAtual/107 
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 * 
	 * {
	 * 		"codigo": "00",
	 * 		"mensagem": "Salto Atual: 970.50"
	 * }
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/consultarSaldoAtual/{numeroConta}")
	public ResponseEntity<ResponseDTO> consultarSaldoAtual(@PathVariable("numeroConta") Long numeroConta) {
		ResponseDTO response = service.consultarSaldoAtual(numeroConta);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	/**
	 * @api {post} /conta/cadastrarConta Cadastra uma conta.
	 * 
	 * @apiParamExample {json} Request-Example: 
	 *{
	 *		"numeroConta": "107",
	 *		"nomeProprietario": "proprietario",
	 *		"cpf": "33311122201",
	 *		"saldo": 1000.00
	 *}
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 * 
	 * {
	 * 		"codigo": "00",
	 * 		"mensagem": "Realizado com sucesso"
	 * }
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/cadastrarConta")
	public ResponseEntity<ResponseDTO> cadastrarConta(@RequestBody Conta conta) {
		ResponseDTO response = service.cadastrarConta(conta);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.CREATED);
	}
	
	/**
	 * @api {delete} /conta/cancelarConta Cancela uma conta informada.
	 * 
	 * @apiParamExample {json} Request-Example: 
	 *{
	 *		"numeroConta": "107",
	 *}
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 * 
	 * {
	 * 		"codigo": "00",
	 * 		"mensagem": "Realizado com sucesso"
	 * }
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/cancelarConta")
	public ResponseEntity<ResponseDTO> cancelarConta(@RequestBody RequestDTO request) {
		ResponseDTO response = service.cancelarConta(request.getNumeroConta());
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	/**
	 * @api {get} /conta/historicoMovimentacao/{numeroConta} Obtém o histórico de uma conta informada.
	 * @apiParamExample {json} Request-Example: 
	 * 
	 * http://localhost:8080/conta/historicoMovimentacao/107
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 * 
	 * [
	 * 	{
	 * 			"dataLancamento": "22/09/2017 17:51:59",
	 * 			"descricao": "Transferência",
	 * 			"valor": 100
	 * 	},
	 * 	{
	 * 			"dataLancamento": "28/09/2017 21:22:36",
	 * 			"descricao": "Receita",
	 * 			"valor": 20
	 * 	},
	 * 	{
	 * 			"dataLancamento": "28/09/2017 21:34:36",
	 * 			"descricao": "Despesa",
	 * 			"valor": -20
	 * 	}
	 * ]
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/historicoMovimentacao/{numeroConta}")
	public ResponseEntity<List<ResponseLancamentoDTO>> historicoMovimentacao(
			@PathVariable("numeroConta") Long numeroConta) {
		List<ResponseLancamentoDTO> response = service.historicoMovimentacao(numeroConta);
		return new ResponseEntity<List<ResponseLancamentoDTO>>(response, HttpStatus.OK);
	}

}
