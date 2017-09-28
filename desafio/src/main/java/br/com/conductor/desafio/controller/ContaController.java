package br.com.conductor.desafio.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.conductor.desafio.dto.AbrirContaRequestDTO;
import br.com.conductor.desafio.dto.ConsultarSaldoResponseDTO;
import br.com.conductor.desafio.dto.ExtratoOperacaoResponseDTO;
import br.com.conductor.desafio.dto.ResponseDTO;
import br.com.conductor.desafio.dto.ResquestDTO;
import br.com.conductor.desafio.dto.TransacaoRequestDTO;
import br.com.conductor.desafio.dto.TransferirRequestDTO;
import br.com.conductor.desafio.exception.ApplicationException;
import br.com.conductor.desafio.service.ContaService;

@RestController
@RequestMapping("/api/conta")
public class ContaController {

	private Logger logger = Logger.getLogger(ContaController.class);

	@Autowired
	private ContaService contaService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> abrirConta(@RequestBody AbrirContaRequestDTO conta) {

		ResponseDTO response = null;

		try {

			response = contaService.abrirConta(conta.getCpf(), conta.getNumeroConta(), conta.getValor());

		} catch (ApplicationException e) {
			e.printStackTrace();
			logger.error(e);
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage()),
					HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "{numeroConta}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO> desativarConta(@PathVariable("numeroConta") String numeroConta) {

		ResponseDTO response = null;

		try {

			response = contaService.desativarConta(numeroConta);

		} catch (ApplicationException e) {
			e.printStackTrace();
			logger.error(e);
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage()),
					HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/consultarSaldo/{numeroConta}", method = RequestMethod.GET)
	public ResponseEntity<ConsultarSaldoResponseDTO> consultarSaldo(@PathVariable("numeroConta") String numeroConta) {

		ConsultarSaldoResponseDTO response = new ConsultarSaldoResponseDTO();

		try {

			response = contaService.consultarSaldo(numeroConta);

		} catch (ApplicationException e) {
			e.printStackTrace();
			logger.error(e);
			response.setResponse(new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage()));
			return new ResponseEntity<ConsultarSaldoResponseDTO>(response, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			response.setResponse(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
			return new ResponseEntity<ConsultarSaldoResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ConsultarSaldoResponseDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/transferir", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> transferencia(@RequestBody TransferirRequestDTO transferencia) {

		ResponseDTO response = null;

		try {

			response = contaService.transferirValor(transferencia.getCpfOrigem(), transferencia.getCpfDestino(),
					transferencia.getContaOrigem(), transferencia.getContaDestino(), transferencia.getValor());

		} catch (ApplicationException e) {
			e.printStackTrace();
			logger.error(e);
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage()),
					HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/extrato", method = RequestMethod.POST)
	public ResponseEntity<ExtratoOperacaoResponseDTO> extratoOperacao(@RequestBody ResquestDTO request) {

		ExtratoOperacaoResponseDTO response = new ExtratoOperacaoResponseDTO();

		try {

			response = contaService.extratoOperacao(request.getCpf(), request.getNumeroConta());

		} catch (ApplicationException e) {
			e.printStackTrace();
			logger.error(e);
			response.setResponse(new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage()));
			return new ResponseEntity<ExtratoOperacaoResponseDTO>(response, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			response.setResponse(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
			return new ResponseEntity<ExtratoOperacaoResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<ExtratoOperacaoResponseDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/transacao", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> adicionarTransacao(@RequestBody TransacaoRequestDTO request) {

		ResponseDTO response = new ResponseDTO();

		try {

			response = contaService.adicionarTransacao(request.getCpf(), request.getNumeroConta(),
					request.getTipoOperacao(), request.getValor(), request.getObservacao());

		} catch (ApplicationException e) {
			e.printStackTrace();
			logger.error(e);
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage()),
					HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

}
