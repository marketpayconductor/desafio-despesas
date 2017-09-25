package br.com.conductor.despesasapp.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.conductor.despesasapp.api.controller.ContaController;
import br.com.conductor.despesasapp.api.controller.validation.ContaControllerValidation;
import br.com.conductor.despesasapp.api.dto.request.CadastroMovimentoRequestDTO;
import br.com.conductor.despesasapp.api.dto.request.ConsultaSaldoRequestDTO;
import br.com.conductor.despesasapp.api.dto.request.HistoricoMovimentacaoRequestDTO;
import br.com.conductor.despesasapp.api.dto.request.TransferenciaRequestDTO;
import br.com.conductor.despesasapp.api.dto.response.CadastroMovimentacaoResponseDTO;
import br.com.conductor.despesasapp.api.dto.response.ConsultaSaldoResponseDTO;
import br.com.conductor.despesasapp.api.dto.response.HistoricoMovimentacaoResponseDTO;
import br.com.conductor.despesasapp.api.dto.response.MovimentoResponseDTO;
import br.com.conductor.despesasapp.api.dto.response.TransferenciaResponseDTO;
import br.com.conductor.despesasapp.domain.model.Conta;
import br.com.conductor.despesasapp.domain.model.Movimento;
import br.com.conductor.despesasapp.domain.model.TipoMovimento;
import br.com.conductor.despesasapp.domain.repository.ContaRepository;
import br.com.conductor.despesasapp.domain.repository.MovimentoRepository;
import br.com.conductor.despesasapp.util.EnumRetorno;

@Service
public class ContaService {

	@Autowired private ContaRepository contaRepository;
	@Autowired private MovimentoRepository movimentoRepository;
	@Autowired private ContaControllerValidation contaControllerValidation;

	private Logger logger = Logger.getLogger(ContaController.class);

	public ResponseEntity<CadastroMovimentacaoResponseDTO> cadastrarMovimentacao(CadastroMovimentoRequestDTO request) {
		CadastroMovimentacaoResponseDTO response = new CadastroMovimentacaoResponseDTO();
		try {
			contaControllerValidation.validateCadastrarMovimentacao(request);
			Conta conta = contaRepository.findByNumero(request.getNumeroConta());
			Movimento movimento = new Movimento
				.Builder(request.getObservacao(), conta, request.getValor())
				.receitaOrDespesa(request.getCodigoTipoMovimentacao())
				.build();

			cadastrarMovimento(movimento);

			response.setRetornoOperacao(EnumRetorno.OPERACAO_REALIZADA_COM_SUCESSO.parse());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			response.setRetornoOperacao(EnumRetorno.ERRO_INTERNO_NA_APLICAO.parse());
			return new ResponseEntity<CadastroMovimentacaoResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CadastroMovimentacaoResponseDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(path = "/consultarSaldo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ConsultaSaldoResponseDTO> consultarSaldo(@RequestBody ConsultaSaldoRequestDTO request) {
		ConsultaSaldoResponseDTO response = new ConsultaSaldoResponseDTO();
		try {
			// TODO validation
			Conta conta = contaRepository.findByNumero(request.getNumeroConta());

			//TODO colocar tratamento para conta não encontrada
			response.setSaldo(conta.getSaldoAtual());
			response.setRetornoOperacao(EnumRetorno.OPERACAO_REALIZADA_COM_SUCESSO.parse());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			response.setRetornoOperacao(EnumRetorno.ERRO_INTERNO_NA_APLICAO.parse());
			return new ResponseEntity<ConsultaSaldoResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ConsultaSaldoResponseDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(path = "/historicoMovimentacoes", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HistoricoMovimentacaoResponseDTO> historicoMovimentacoes(@RequestBody HistoricoMovimentacaoRequestDTO request) {
		HistoricoMovimentacaoResponseDTO response = new HistoricoMovimentacaoResponseDTO();
		try {
			// TODO validation
			Conta conta = contaRepository.findByNumero(request.getNumeroConta());
			List<Movimento> movimentacoes = movimentoRepository.findByConta(conta);
			List<MovimentoResponseDTO> movimentacoesResponse = new ArrayList<>();

			movimentacoes.forEach(mov -> {
				movimentacoesResponse.add(new MovimentoResponseDTO(mov));
			});

			response.setListaDeMovimentacoes(movimentacoesResponse);
			response.setRetornoOperacao(EnumRetorno.OPERACAO_REALIZADA_COM_SUCESSO.parse());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			response.setRetornoOperacao(EnumRetorno.ERRO_INTERNO_NA_APLICAO.parse());
			return new ResponseEntity<HistoricoMovimentacaoResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<HistoricoMovimentacaoResponseDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(path = "/transferirSaldo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<TransferenciaResponseDTO> transferirSaldoEntreContas(@RequestBody TransferenciaRequestDTO request) {
		TransferenciaResponseDTO response = new TransferenciaResponseDTO();
		try {
			// TODO validation
			Conta contaOrigemSaldo = contaRepository.findByNumero(request.getNumeroContaOrigem());
			Conta contaDestinoSaldo = contaRepository.findByNumero(request.getNumeroContaDestino());

			Movimento movimentoContaOrigem = new Movimento
					.Builder(TipoMovimento.OBS_TRANSFERENCIA_SALDO_PARA_OUTRA_CONTA, contaOrigemSaldo, request.getValor())
					.receitaOrDespesa(TipoMovimento.TRANSFERENCIA_SALDO_DEBITO)
					.build();

			Movimento movimentoContaDestino = new Movimento
					.Builder(TipoMovimento.OBS_TRANSFERENCIA_SALDO_DE_OUTRA_CONTA, contaDestinoSaldo, request.getValor())
					.receitaOrDespesa(TipoMovimento.TRANSFERENCIA_SALDO_CREDITO)
					.build();

			cadastrarMovimento(movimentoContaOrigem);
			cadastrarMovimento(movimentoContaDestino);

			response.setRetornoOperacao(EnumRetorno.OPERACAO_REALIZADA_COM_SUCESSO.parse());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			response.setRetornoOperacao(EnumRetorno.ERRO_INTERNO_NA_APLICAO.parse());
			return new ResponseEntity<TransferenciaResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<TransferenciaResponseDTO>(response, HttpStatus.OK);
	}

	public void cadastrarMovimento(Movimento movimento) {
		Conta conta = movimento.getConta();
		movimentoRepository.save(movimento);
		atualizarSaldoConta(conta, movimento);
	}

	private void atualizarSaldoConta(Conta conta, Movimento movimento) {
		BigDecimal saldoAtual = conta.getSaldoAtual();
		conta.setSaldoAnterior(saldoAtual);
		if (movimento.isMovimentoCredito()) {
			conta.setSaldoAtual(saldoAtual.add(movimento.getValor()));
		} else {
			conta.setSaldoAtual(saldoAtual.subtract(movimento.getValor()));
		}
		contaRepository.save(conta);
	}

}
