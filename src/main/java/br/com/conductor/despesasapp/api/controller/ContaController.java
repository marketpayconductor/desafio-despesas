package br.com.conductor.despesasapp.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.conductor.despesasapp.api.dto.request.CadastroMovimentoRequestDTO;
import br.com.conductor.despesasapp.api.dto.request.ConsultaSaldoRequestDTO;
import br.com.conductor.despesasapp.api.dto.request.HistoricoMovimentacaoRequestDTO;
import br.com.conductor.despesasapp.api.dto.request.TransferenciaRequestDTO;
import br.com.conductor.despesasapp.api.dto.response.CadastroMovimentacaoResponseDTO;
import br.com.conductor.despesasapp.api.dto.response.ConsultaSaldoResponseDTO;
import br.com.conductor.despesasapp.api.dto.response.HistoricoMovimentacaoResponseDTO;
import br.com.conductor.despesasapp.api.dto.response.TransferenciaResponseDTO;
import br.com.conductor.despesasapp.service.ContaService;

@RestController
@RequestMapping("/api/conta")
public class ContaController {
	
	@Autowired private ContaService contaService;
	
	/**
	 * @api {post} /api/conta/cadastrarMovimento Cadastrar movimentações da contas.	
	 * @apiName Cadastrar Movimentações
	 * @apiGroup Movimentacao
	 * @apiVersion 1.0.0
	 * 
	 * @apiParam {BigDecimal} valor Valor da movimentação
	 * @apiParam {long} numeroConta Nomero da conta da movimentação
	 * @apiParam {String} cpf CPF pessoa da conta
	 * @apiParam {long} codigoTipoMovimentacao Código referente ao tipo da movimentação
	 * @apiParam {long} observacao Observação da movimentação
	 * 
	 * @apiSuccess {String} retornoOperacao Mensagem de retorno.
	 * 
	 * @apiParamExample {json} Request-Example: 
	 *{
	 *	"valor" : 100.0,
	 *	"numeroConta" : "12345",
	 *	"cpf" : "83017734803",
	 *	"codigoTipoMovimentacao" : -20,
	 *	"observacao" : "Teste de cadastro de crédito"
	 *}
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 *{
	 *	"retornoOperacao":{
	 *	"codigo": "00",
	 *	"mensagem": "Operação realizada com sucesso!"
	 *	}
	 *}
	 */
	@ResponseBody
	@RequestMapping(path = "/cadastrarMovimento", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarMovimentacao(@Valid @RequestBody CadastroMovimentoRequestDTO request) {
		ResponseEntity<CadastroMovimentacaoResponseDTO> response = contaService.cadastrarMovimentacao(request);
		return response;
	}
	
	/**
	 * @api {post} /api/conta/consultarSaldo Consultar saldo da conta.	
	 * @apiName Consultar saldo
	 * @apiGroup Saldo
	 * @apiVersion 1.0.0
	 * 
	 * @apiParam {long} numeroConta Nomero da conta da movimentação
	 * 
	 * @apiSuccess {BigDecimal} saldo Saldo atual da conta
	 * @apiSuccess {String} retornoOperacao Mensagem de retorno
	 * 
	 * @apiParamExample {json} Request-Example: 
	 *{
	 *	"numeroConta" : "12345"
	 *}
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 *{
	 *	"retornoOperacao":{
	 *	"codigo": "00",
     *	"mensagem": "Operação realizada com sucesso!"
	 *	},
	 *	"saldo": 14.5
	 *}
	 */
	@ResponseBody
	@RequestMapping(path = "/consultarSaldo", method = RequestMethod.POST)
	public ResponseEntity<ConsultaSaldoResponseDTO> consultarSaldo(@RequestBody ConsultaSaldoRequestDTO request) {
		ResponseEntity<ConsultaSaldoResponseDTO> response = contaService.consultarSaldo(request);
		return response;
	}

	/**
	 * @api {post} /api/conta/historicoMovimentacoes Histórico das movimentações da conta.	
	 * @apiName Histórico de Movimentações
	 * @apiGroup Movimentacao
	 * @apiVersion 1.0.0
	 * 
	 * @apiParam {long} numeroConta Nomero da conta da movimentação
	 * 
	 * @apiSuccess {String} retornoOperacao Mensagem de retorno
	 * 
	 * @apiParamExample {json} Request-Example: 
	 *	{
	 *		"numeroConta" : "12345"
	 *	}
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 *{
	 *	"retornoOperacao":{
     *	"codigo": "00",
     *	"mensagem": "Operação realizada com sucesso!"
     *	},
     *	"listaDeMovimentacoes":[
     *		{"data": "24/09/2017", "observacao": "Transerência de saldo de outra conta", "codigoTipoMovimentacao": -40, "valor": 100…},
     *		{"data": "24/09/2017", "observacao": "Transferência de saldo para outra conta", "codigoTipoMovimentacao": -30,…},
     *		{"data": "24/09/2017", "observacao": "Transerência de saldo de outra conta", "codigoTipoMovimentacao": -40, "valor": 100…}
     *	]
     *}
	 */
	@ResponseBody
	@RequestMapping(path = "/historicoMovimentacoes", method = RequestMethod.POST)
	public ResponseEntity<HistoricoMovimentacaoResponseDTO> historicoMovimentacoes(@RequestBody HistoricoMovimentacaoRequestDTO request) {
		ResponseEntity<HistoricoMovimentacaoResponseDTO> response = contaService.historicoMovimentacoes(request);
		return response;
	}

	/**
	 * @api {post} /api/conta/transferirSaldo Tranferência de saldo entre contas.	
	 * @apiName Transferência de saldo
	 * @apiGroup Saldo
	 * @apiVersion 1.0.0
	 * 
	 * @apiParam {BigDecimal} valor Valor da movimentação
	 * @apiParam {long} numeroContaOrigem Nomero da conta de origigem da movimentação
	 * @apiParam {String} cpfContaOrigem CPF pessoa da conta de origem
	 * @apiParam {long} numeroContaDestino Nomero da conta de destino da movimentação
	 * @apiParam {String} cpfContaDestino CPF pessoa da conta de destino
	 * 
	 * @apiSuccess {String} retornoOperacao Mensagem de retorno
	 * 
	 * @apiParamExample {json} Request-Example: 
	 *{
  	 *	"numeroContaOrigem" : 12345,
  	 *	"cpfContaOrigem" : "01651247412",
	 *	"numeroContaDestino" : 54321,
	 *	"cpfContaDestino" : "23197456700",
  	 *	"valor" : 10.0
	 *	}
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 *{
	 *	"retornoOperacao":{
	 *	"codigo": "00",
	 *	"mensagem": "Operação realizada com sucesso!"
	 *	}
	 *}
	 */
	@ResponseBody
	@RequestMapping(path = "/transferirSaldo", method = RequestMethod.POST)
	public ResponseEntity<TransferenciaResponseDTO> transferirSaldoEntreContas(@RequestBody TransferenciaRequestDTO request) {
		ResponseEntity<TransferenciaResponseDTO> response = contaService.transferirSaldoEntreContas(request);
		return response;
	}
	
}
