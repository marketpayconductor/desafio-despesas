package br.com.conductor.desafio.service.serviceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.conductor.desafio.dto.ConsultarSaldoResponseDTO;
import br.com.conductor.desafio.dto.ExtratoOperacaoResponseDTO;
import br.com.conductor.desafio.dto.OperacaoResponseDTO;
import br.com.conductor.desafio.dto.ResponseDTO;
import br.com.conductor.desafio.exception.ApplicationException;
import br.com.conductor.desafio.exception.ContaDesativadaException;
import br.com.conductor.desafio.exception.ContaNaoEncontradaException;
import br.com.conductor.desafio.exception.CpfNaoInformadoException;
import br.com.conductor.desafio.exception.CpfNaoPertenceContaException;
import br.com.conductor.desafio.exception.NumeroContaNaoInformado;
import br.com.conductor.desafio.exception.SaldoIndisponivelException;
import br.com.conductor.desafio.exception.TipoOperacaoException;
import br.com.conductor.desafio.exception.UsuarioDesativadoException;
import br.com.conductor.desafio.exception.UsuarioNaoEncontradoException;
import br.com.conductor.desafio.exception.ValorNaoInformado;
import br.com.conductor.desafio.model.Conta;
import br.com.conductor.desafio.model.Operacao;
import br.com.conductor.desafio.model.Usuario;
import br.com.conductor.desafio.repository.ContaRepository;
import br.com.conductor.desafio.repository.OperacaoRepository;
import br.com.conductor.desafio.repository.UsuarioRepository;
import br.com.conductor.desafio.service.ContaService;
import br.com.conductor.desafio.util.Parser;
import br.com.conductor.desafio.util.TipoOperacao;

@Service
public class ContaServiceImpl implements ContaService {

	private Logger logger = Logger.getLogger(ContaServiceImpl.class);

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private OperacaoRepository operacaoRepository;

	@Autowired
	private Parser parser;

	@Override
	public ResponseDTO abrirConta(String cpf, String numeroConta, BigDecimal valor) throws ApplicationException {

		ResponseDTO response = null;

		verificaCpf(cpf);

		verificaNumeroConta(numeroConta);

		verificaValorInformado(valor);

		try {

			Usuario usuario = usuarioRepository.findByCpf(cpf);

			verificaUsuarioExiste(usuario);

			if (contaRepository.findByNumeroConta(numeroConta) != null) {
				throw new NumeroContaNaoInformado();
			}

			contaRepository.save(parser.contaParser(usuario, numeroConta, valor));

			response = new ResponseDTO(HttpStatus.OK, "Conta criada com sucesso.");

		} catch (Exception e) {
			logger.error(e);
			throw e;
		}

		return response;
	}

	@Override
	public ResponseDTO desativarConta(String numeroConta) throws ApplicationException {

		ResponseDTO response = null;

		verificaNumeroConta(numeroConta);

		try {

			Conta conta = contaRepository.findByNumeroConta(numeroConta);

			if (conta != null) {

				conta.setAtiva(false);
				conta.setDataDesativada(new Date());
				contaRepository.save(conta);

				response = new ResponseDTO(HttpStatus.OK, "Conta desativada com sucesso.");

			} else {
				throw new ContaNaoEncontradaException();
			}

		} catch (Exception e) {
			logger.error(e);
			throw e;
		}

		return response;
	}

	@Override
	public ConsultarSaldoResponseDTO consultarSaldo(String numeroConta) throws ApplicationException {

		ConsultarSaldoResponseDTO response = new ConsultarSaldoResponseDTO();

		verificaNumeroConta(numeroConta);

		try {

			Conta conta = contaRepository.findByNumeroConta(numeroConta);

			if (conta != null) {

				if (!conta.isAtiva()) {
					throw new ContaDesativadaException();
				}

				response.setSaldo(conta.getSaldo());

				response.setResponse(new ResponseDTO(HttpStatus.OK, "Operação realizada com sucesso"));

			} else {
				throw new ContaNaoEncontradaException();
			}

		} catch (Exception e) {
			logger.error(e);
			throw e;
		}

		return response;
	}

	@Override
	public ResponseDTO transferirValor(String cpfOrigem, String cpfDestino, String contaOrigem, String contaDestino,
			BigDecimal valor) throws ApplicationException {

		ResponseDTO response = null;

		verificaCpf(cpfOrigem);
		verificaCpf(cpfDestino);

		verificaNumeroConta(contaOrigem);
		verificaNumeroConta(contaDestino);

		verificaValorInformado(valor);

		try {

			Usuario usuarioOrigem = usuarioRepository.findByCpf(cpfOrigem);
			Usuario usuarioDestino = usuarioRepository.findByCpf(cpfDestino);

			verificaUsuarioExiste(usuarioOrigem);
			verificaUsuarioExiste(usuarioDestino);

			Conta contaO = contaRepository.findByNumeroConta(contaOrigem);
			Conta contaD = contaRepository.findByNumeroConta(contaDestino);

			verificaContaExistente(contaO);
			verificaContaExistente(contaD);

			verificaSaldoDisponivel(contaO, valor);

			BigDecimal saldoContaOrigem = contaO.getSaldo().subtract(valor);
			contaO.setSaldo(saldoContaOrigem);
			contaRepository.save(contaO);

			BigDecimal saldoContaDestino = contaD.getSaldo().add(valor);
			contaD.setSaldo(saldoContaDestino);
			contaRepository.save(contaD);

			Operacao operacaoCredito = new Operacao();
			operacaoCredito.setConta(contaD);
			operacaoCredito.setDataOperacao(new Date());
			operacaoCredito.setTipoOperacao(TipoOperacao.TRANSFERENCIA_CREDITO);
			operacaoCredito.setValorOperacao(valor);
			operacaoCredito.setObservacao("Transferencia de " + usuarioOrigem.getNome());

			operacaoRepository.save(operacaoCredito);

			Operacao operacaoDebito = new Operacao();
			operacaoDebito.setConta(contaO);
			operacaoDebito.setDataOperacao(new Date());
			operacaoDebito.setTipoOperacao(TipoOperacao.TRANSFERENCIA_DEBITO);
			operacaoDebito.setValorOperacao(valor);
			operacaoDebito.setObservacao("Transferencia para " + usuarioDestino.getNome());

			operacaoRepository.save(operacaoDebito);

			response = new ResponseDTO(HttpStatus.OK, "Operação realizada com sucesso.");

		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		
		return response;
	}

	@Override
	public ExtratoOperacaoResponseDTO extratoOperacao(String cpf, String numeroConta) throws ApplicationException {

		ExtratoOperacaoResponseDTO response = new ExtratoOperacaoResponseDTO();

		verificaCpf(cpf);
		verificaNumeroConta(numeroConta);

		try {

			Usuario usuario = usuarioRepository.findByCpf(cpf);

			verificaUsuarioExiste(usuario);

			Conta conta = contaRepository.findByNumeroConta(numeroConta);

			verificaContaExistente(conta);

			verificaCpfPertenceConta(usuario, conta);

			List<OperacaoResponseDTO> operacoesDTO = parser.operacaoParser(operacaoRepository.findByConta(conta));

			response.setCpf(usuario.getCpf());
			response.setUsuario(usuario.getNome());
			response.setNumeroConta(conta.getNumeroConta());
			response.setOperacoes(operacoesDTO);
			response.setSaldoAtual(conta.getSaldo());

			response.setResponse(new ResponseDTO(HttpStatus.OK, "Operação realizada com sucesso"));

		} catch (Exception e) {
			logger.error(e);
			throw e;
		}

		return response;
	}

	@Override
	public ResponseDTO adicionarTransacao(String cpf, String numeroConta, Integer tipoOperacao, BigDecimal valor,
			String observacao) throws ApplicationException {

		ResponseDTO response = null;

		verificaCpf(cpf);
		verificaNumeroConta(numeroConta);
		verificaValorInformado(valor);
		TipoOperacao tipo = verificarTipoOperacao(tipoOperacao);

		try {

			Usuario usuario = usuarioRepository.findByCpf(cpf);

			verificaUsuarioExiste(usuario);

			Conta conta = contaRepository.findByNumeroConta(numeroConta);

			verificaContaExistente(conta);

			verificaCpfPertenceConta(usuario, conta);

			BigDecimal saldo;
			if (tipo == TipoOperacao.DESPESA) {
				if (conta.getSaldo().compareTo(valor) < 0) {
					throw new SaldoIndisponivelException();
				}
				saldo = conta.getSaldo().subtract(valor);
			} else {
				saldo = conta.getSaldo().add(valor);
			}

			conta.setSaldo(saldo);
			contaRepository.save(conta);

			Operacao operacao = new Operacao();
			operacao.setConta(conta);
			operacao.setDataOperacao(new Date());
			operacao.setTipoOperacao(tipo);
			operacao.setValorOperacao(valor);
			operacao.setObservacao(observacao);

			operacaoRepository.save(operacao);

			response = new ResponseDTO(HttpStatus.OK, "Operação realizada com sucesso.");

		} catch (Exception e) {
			logger.error(e);
			throw e;
		}

		return response;
	}

	private void verificaCpf(String cpf) throws ApplicationException {
		if (StringUtils.isEmpty(cpf)) {
			throw new CpfNaoInformadoException();
		}
	}

	private void verificaNumeroConta(String numeroConta) throws ApplicationException {
		if (StringUtils.isEmpty(numeroConta)) {
			throw new NumeroContaNaoInformado();
		}
	}

	private void verificaValorInformado(BigDecimal valor) throws ApplicationException {
		if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new ValorNaoInformado();
		}
	}

	private void verificaUsuarioExiste(Usuario usuario) throws ApplicationException {

		if (usuario == null) {
			throw new UsuarioNaoEncontradoException();
		}

		if (!usuario.isAtivo()) {
			throw new UsuarioDesativadoException();
		}
	}

	private void verificaCpfPertenceConta(Usuario usuario, Conta conta) throws ApplicationException {

		if (!usuario.getIdUsuario().equals(conta.getUsuario().getIdUsuario())) {
			throw new CpfNaoPertenceContaException();
		}
	}

	private void verificaSaldoDisponivel(Conta conta, BigDecimal valor) throws ApplicationException {

		if (conta.getSaldo().compareTo(valor) <= 0) {
			throw new SaldoIndisponivelException();
		}
	}

	private void verificaContaExistente(Conta conta) throws ApplicationException {
		if (conta == null) {
			throw new ContaNaoEncontradaException();
		}

		if (!conta.isAtiva()) {
			throw new ContaDesativadaException();
		}
	}

	private TipoOperacao verificarTipoOperacao(Integer tipo) throws ApplicationException {

		if (tipo == null) {
			throw new TipoOperacaoException();
		} else if (tipo == TipoOperacao.DESPESA.ordinal()) {
			return TipoOperacao.DESPESA;
		} else if (tipo == TipoOperacao.RECEITA.ordinal()) {
			return TipoOperacao.RECEITA;
		} else {
			throw new TipoOperacaoException();
		}
	}

}
