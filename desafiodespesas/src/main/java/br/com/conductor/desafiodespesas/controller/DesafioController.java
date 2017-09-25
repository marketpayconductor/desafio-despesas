package br.com.conductor.desafiodespesas.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.conductor.desafiodespesas.domain.conta.Conta;
import br.com.conductor.desafiodespesas.domain.conta.ContaRepository;
import br.com.conductor.desafiodespesas.dto.RequestCadastrarContaDTO;
import br.com.conductor.desafiodespesas.dto.RequestDefaultApiDTO;
import br.com.conductor.desafiodespesas.dto.RequestTransferirCreditoDTO;
import br.com.conductor.desafiodespesas.dto.ResponseDefaultApiDTO;
import br.com.conductor.desafiodespesas.enums.Codigos;
import br.com.conductor.desafiodespesas.exception.ApplicationException;
import br.com.conductor.desafiodespesas.service.DesafioService;


/**
 * 
 * @author luiz
 *
 */

@RestController
@RequestMapping(path = "/api")
public class DesafioController {
	
	@Autowired	
	private DesafioService desafioService;
	
	@Autowired
	private ContaRepository contaRepository;	
	
	private Logger logger = Logger.getLogger(DesafioController.class);

	
	
	
	/**
	 * @api {post} /api/adicionar 
	 * @apiName Adicionar Despesa e Receita
	 * @apiDescription Responsável por adicionar valores referentes a dispesas e receitas do usuário.
	 * 
	 * @apiParam {String} cpf: cpf do titular da conta
	 * @apiParam {String} numeroconta: número da conta do titula que será utilizada para efetuar a transação
	 * @apiParam {BigDecimal} valor: valor da Despesa/Reeita
	 * @apiParam {String} codigooperacao: Código da Operação (1 - Despesa, 2 - Receita)
	 * @apiParam {String} observacao: descrição para critério informativo
	 * 
	 * 
	 * @apiSuccess {String} codigoResposta: Codigo de resposta.
	 * @apiSuccess {String} mensagemResposta: Descricao do código.
	 *
	 * @apiParamExample {json} Request-Example: 
	 *   {
	 *       "cpf":"09031850403",
  	 *		 "numeroconta":"3333",  
  	 *		 "valor":150.00 ,
  	 *		 "codigooperacao":2,
  	 *		 "observacao": "Teste para adição de despesas 3"
	 *   }
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 * 
	 *	 {
	 *		 "codigoResposta": "00",
	 *		 "mensagemReposta": "OPERAÇÃO REALIZADA COM SUCESSO"
	 *   }
	 */
	
	@RequestMapping(method = RequestMethod.POST, path = "/adicionar")
	public ResponseEntity<ResponseDefaultApiDTO> adicionarDespesaReceita(@RequestBody RequestDefaultApiDTO request) {
		
		ResponseDefaultApiDTO response = new ResponseDefaultApiDTO();
		
		try {
			Conta conta = contaRepository.findByNumeroConta(request.getNumeroConta());
			
			if(conta !=null) {
				
				if(request.getCpf().equals(conta.getCpf())) {
					response = desafioService.adicionarReceitaDespesas(conta, request.getCodigoopeacao(), request.getValor(), request.getObservacao());
				} else {
					throw new ApplicationException(Codigos.CPF_NAO_PERTENCENTE_A_CONTA.getCodigo(), Codigos.CPF_NAO_PERTENCENTE_A_CONTA.getMensagem());

				}				
				
			} else {
				throw new ApplicationException(Codigos.CONTA_INEXISTENTE.getCodigo(), Codigos.CONTA_INEXISTENTE.getMensagem());
			}
			
		} catch (ApplicationException e) {
			logger.error(e);
			response.setCodigoResposta(e.getCodigoErro());
			response.setMensagemReposta(e.getMensagemErro());
			
		} catch (Exception e) {
			logger.error(e);
			response.setCodigoResposta(Codigos.ERRO_PROCESSAMENTO_INTERNO.getCodigo());
			response.setMensagemReposta(Codigos.ERRO_PROCESSAMENTO_INTERNO.getMensagem());
		}
		return new ResponseEntity<ResponseDefaultApiDTO>(response, HttpStatus.OK);
	}
	

	/**
	 * @api {post} /api/consultar 
	 * @apiName Consulta Saldo Conta
	 * @apiDescription Responsável por consultar o saldo referente a conta informada.
	 * 
	 * @apiParam {String} cpf: cpf do titular da conta
	 * @apiParam {String} numeroconta: número da conta do titula que será utilizada para efetuar a transação
	 *
	 * 
	 * @apiSuccess {String} codigoResposta: Codigo de resposta.
	 * @apiSuccess {String} mensagemResposta: Descricao do código.
	 * @apiSuccess {BigDecimal} saldoAtual: Saldo Atual das Contas.
	 *
	 * @apiParamExample {json} Request-Example: 
	 *   
	 *   {
  	 *		"cpf":"94250248470",
  	 *		"numeroconta":"4444"
     *
	 *	}
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 * 
	 *	{
	 *		"codigoResposta": "00",
	 *		"mensagemReposta": "OPERAÇÃO REALIZADA COM SUCESSO",
	 *		"saldoAtual": 200
	 *	}
	 */	
	
	@RequestMapping(method = RequestMethod.POST, path = "/consultar")
	public ResponseEntity<ResponseDefaultApiDTO> consultarSaldo(@RequestBody RequestDefaultApiDTO request) {
		
		ResponseDefaultApiDTO response = new ResponseDefaultApiDTO();
		
		try {
			Conta conta = contaRepository.findByNumeroConta(request.getNumeroConta());
			
			if(conta !=null) {
				
				if(request.getCpf().equals(conta.getCpf())) {
					response = desafioService.consultaSaldoConta(conta);
				} else {
					throw new ApplicationException(Codigos.CPF_NAO_PERTENCENTE_A_CONTA.getCodigo(), Codigos.CPF_NAO_PERTENCENTE_A_CONTA.getMensagem());

				}				
				
			} else {
				throw new ApplicationException(Codigos.CONTA_INEXISTENTE.getCodigo(), Codigos.CONTA_INEXISTENTE.getMensagem());
			}
		} catch (ApplicationException e) {
			logger.error(e);
			response.setCodigoResposta(e.getCodigoErro());
			response.setMensagemReposta(e.getMensagemErro());		
			
		} catch (Exception e) {
			logger.error(e);
			response.setCodigoResposta(Codigos.ERRO_PROCESSAMENTO_INTERNO.getCodigo());
			response.setMensagemReposta(Codigos.ERRO_PROCESSAMENTO_INTERNO.getMensagem());
		}
		return new ResponseEntity<ResponseDefaultApiDTO>(response, HttpStatus.OK);
	}

	/**
	 * @api {post} /api/extrato 
	 * @apiName Extrato Movimentacao
	 * @apiDescription Responsável por consultar e retornar toda a movimentação em relação Despesa/Receita
	 * @apiParam {String} cpf: cpf do titular da conta
	 * @apiParam {String} numeroconta: número da conta do titula que será utilizada para efetuar a transação
	 *
	 * 
	 * @apiSuccess {String} codigoResposta: Codigo de resposta.
	 * @apiSuccess {String} mensagemResposta: Descricao do código.
	 * @apiSuccess {BigDecimal} saldoAtual: Saldo Atual das Contas.
	 * @apiSuccess {List} lista: Lista de todas as movimentações.
	 *
	 * @apiParamExample {json} Request-Example: 
	 *   
	 *   {
  	 *		"cpf":"94250248470",
  	 *		"numeroconta":"4444"
     *
	 *	}
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 * 
	 *	{
	 *		"codigoResposta": "00",
	 *		"mensagemReposta": "OPERAÇÃO REALIZADA COM SUCESSO",
	 *		"saldoAtual": 200,
	 *		"lista":[
	 *					{
	 *						"valor": 150,
	 *						"dataMovimentacao": "24/09/2017 22:15:42",
	 *						"observacao": "Internet",
	 *						"tipoOperacao": "despesa",
	 *						"codigoDespesa": 1
	 *					}
	 *				]
	 *	}
	 */	
	
	@RequestMapping(method = RequestMethod.POST, path = "/extrato")
	public ResponseEntity<ResponseDefaultApiDTO> extratoMovimentaao(@RequestBody RequestDefaultApiDTO request) {
		
		ResponseDefaultApiDTO response = new ResponseDefaultApiDTO();
		
		try {
			Conta conta = contaRepository.findByNumeroConta(request.getNumeroConta());
			
			if(conta !=null) {
				
				if(request.getCpf().equals(conta.getCpf())) {
					response = desafioService.consultaMovimentacao(conta);
					
				} else {
					throw new ApplicationException(Codigos.CPF_NAO_PERTENCENTE_A_CONTA.getCodigo(), Codigos.CPF_NAO_PERTENCENTE_A_CONTA.getMensagem());

				}				
				
			} else {
				throw new ApplicationException(Codigos.CONTA_INEXISTENTE.getCodigo(), Codigos.CONTA_INEXISTENTE.getMensagem());
			}
		} catch (ApplicationException e) {
			logger.error(e);
			response.setCodigoResposta(e.getCodigoErro());
			response.setMensagemReposta(e.getMensagemErro());				
		
			
		} catch (Exception e) {
			logger.error(e);
			response.setCodigoResposta(Codigos.ERRO_PROCESSAMENTO_INTERNO.getCodigo());
			response.setMensagemReposta(Codigos.ERRO_PROCESSAMENTO_INTERNO.getMensagem());
		}
		return new ResponseEntity<ResponseDefaultApiDTO>(response, HttpStatus.OK);
	}
	

	/**
	 * @api {post} /api/transferir 
	 * @apiName Transferir Valor 
	 * @apiDescription Responsável por transferir valores entre duas contas.
	 * 
	 * @apiParam {String} cpfContaAreceber: cpf do titular da conta que irá receber o valor.
	 * @apiParam {String} cpfContaAtransferir: cpf do titular da conta que irá transferir o valor.
	 * @apiParam {String} numeroContaAreceber: Número da conta que será depositado o valor informado.
	 * @apiParam {String} numeroContaAtransferir: Número da conta que será debitado o valor informado para a transfêrencia.
	 * @apiParam {String} valorTransferencia: Valor correspondente a transferência.
	 *
	 * 
	 * @apiSuccess {String} codigoResposta: Codigo de resposta.
	 * @apiSuccess {String} mensagemResposta: Descricao do código.
	 *
	 * @apiParamExample {json} Request-Example: 
	 *  {
  	 *		"cpfContaAreceber":"10166359408",
  	 *		"cpfContaAtransferir":"09031850403",	 
  	 *		"numeroContaAreceber":"1111"     
  	 *		"numeroContaAtransferir":"3333",	 
  	 *		"valorTransferencia":150.00    	 
	 *	}
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 * 
	 *	 {
	 *		 "codigoResposta": "00",
	 *		 "mensagemReposta": "OPERAÇÃO REALIZADA COM SUCESSO"
	 *   }
	 */	
	
	@RequestMapping(method = RequestMethod.POST, path = "/transferir")
	public ResponseEntity<ResponseDefaultApiDTO> transferirValor(@RequestBody RequestTransferirCreditoDTO request) {
		
		ResponseDefaultApiDTO response = new ResponseDefaultApiDTO();
		
		try {
			
			Conta contaAreceber = contaRepository.findByNumeroConta(request.getNumeroContaAreceber());
			
			Conta contaAtransferir = contaRepository.findByNumeroConta(request.getNumeroContaAtransferir());

			
			if(contaAreceber !=null && contaAtransferir != null) {				
				if(request.getCpfContaAreceber().equals(contaAreceber.getCpf()) && 
				   request.getCpfContaAtransferir().equals(contaAtransferir.getCpf())) {
					
					response = desafioService.transferir(contaAtransferir, contaAreceber, request.getValorTransferencia());
					
				} else {
					throw new ApplicationException(Codigos.CPF_NAO_PERTENCENTE_A_CONTA.getCodigo(), Codigos.CPF_NAO_PERTENCENTE_A_CONTA.getMensagem());

				}				
				
			} else {
				throw new ApplicationException(Codigos.CONTA_INEXISTENTE.getCodigo(), Codigos.CONTA_INEXISTENTE.getMensagem());
			}
		} catch (ApplicationException e) {
			logger.error(e);
			response.setCodigoResposta(e.getCodigoErro());
			response.setMensagemReposta(e.getMensagemErro());				
		
			
		} catch (Exception e) {
			logger.error(e);
			response.setCodigoResposta(Codigos.ERRO_PROCESSAMENTO_INTERNO.getCodigo());
			response.setMensagemReposta(Codigos.ERRO_PROCESSAMENTO_INTERNO.getMensagem());
		}
		return new ResponseEntity<ResponseDefaultApiDTO>(response, HttpStatus.OK);
	}
	
	

	/**
	 * @api {post} /api/cadastrar 
	 * @apiName Adicionar uma nova conta
	 * @apiDescription Responsável por criar uma nova conta
	 * 
	 * 
	 * @apiParam {String} cpf: cpf do titular da conta.
	 * @apiParam {String} numeroconta: número da conta do titula que será utilizada para efetuar a transação.
	 * @apiParam {BigDecimal} sadoconta:saldo inicial da conta.
	 *
	 * 
	 * 
	 * @apiSuccess {String} codigoResposta: Codigo de resposta.
	 * @apiSuccess {String} mensagemResposta: Descricao do código.
	 *
	 * @apiParamExample {json} Request-Example: 
	 *		
	 *		{
  	 *			"cpf":"94250248470",
  	 *			"numeroconta":"4444",  
  	 *			"saldoconta":0  
	 *		}
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 * 
	 *	 {
	 *		 "codigoResposta": "00",
	 *		 "mensagemReposta": "OPERAÇÃO REALIZADA COM SUCESSO"
	 *   }
	 */

	@RequestMapping(method = RequestMethod.POST, path = "cadastrar" )
	public ResponseEntity<ResponseDefaultApiDTO> cadastrarConta(@RequestBody RequestCadastrarContaDTO request) {		
		
		ResponseDefaultApiDTO response = new ResponseDefaultApiDTO();	
		
		try {
		 
			response = desafioService.cadastrarConta(request.getNumeroConta(), request.getCpf(), request.getSaldoConta());
	
		} catch (Exception e) {
			logger.error(e);
			response.setCodigoResposta(Codigos.ERRO_PROCESSAMENTO_INTERNO.getCodigo());
			response.setMensagemReposta(Codigos.ERRO_PROCESSAMENTO_INTERNO.getMensagem());
		}
		return new ResponseEntity<ResponseDefaultApiDTO>(response, HttpStatus.OK);
		
	}
}
