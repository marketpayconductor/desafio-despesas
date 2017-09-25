package br.com.conductor.selecao.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.conductor.selecao.dto.SalvarMovimentacaoContaDTO;
import br.com.conductor.selecao.exception.EntidadeNaoEncontrada;
import br.com.conductor.selecao.exception.ParametroRequestInvalidoException;
import br.com.conductor.selecao.model.Conta;
import br.com.conductor.selecao.model.LancamentoContaMovimentacao;
import br.com.conductor.selecao.model.MovimentacaoConta;
import br.com.conductor.selecao.model.TipoLancamentoConta;
import br.com.conductor.selecao.model.Usuario;
import br.com.conductor.selecao.repository.MovimentacaoContaRepository;

@Service("movimentacaoContaService")
@Transactional
public class MovimentacaoContaServiceImpl implements MovimentacaoContaService {

	@Autowired
	private MovimentacaoContaRepository movimentacaoContaRepository;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	ContaService contaService;

	@Autowired
	LancamentoContaMovimentacaoService lancamentoContaMovimentacaoService;

	@Override
	public MovimentacaoConta pesquisarMovimentacaoContaPorId(Long id) {
		return movimentacaoContaRepository.findOne(id);
	}

	@Override
	public List<MovimentacaoConta> listarMovimentacaoContas() {
		return movimentacaoContaRepository.findAll();
	}

	private void salvarMovimentacaoConta(MovimentacaoConta conta) {
		movimentacaoContaRepository.save(conta);
	}

	@Override
	public MovimentacaoConta salvarMovimentacaoConta(SalvarMovimentacaoContaDTO salvarMovimentacaoContaDTO)
			throws EntidadeNaoEncontrada, ParametroRequestInvalidoException {

		Conta contaOrigem = contaService.pesquisarContaPorId(salvarMovimentacaoContaDTO.getCodigoContaOrigem());
		if (contaOrigem == null) {
			throw new EntidadeNaoEncontrada(Conta.class);
		}

		Conta contaDestino = contaService.pesquisarContaPorId(salvarMovimentacaoContaDTO.getCodigoContaDestino());
		if (contaDestino == null) {
			throw new EntidadeNaoEncontrada(Conta.class);
		}

		Usuario usuario = usuarioService.pesquisarUsuarioPorId(salvarMovimentacaoContaDTO.getCodigoUsuario());
		if (usuario == null) {
			throw new EntidadeNaoEncontrada(Usuario.class);
		}

		Calendar calendar = Calendar.getInstance();

		MovimentacaoConta movimentacaoConta = new MovimentacaoConta();
		movimentacaoConta.setDataCadastro(calendar);
		movimentacaoConta.setContaOrigem(contaOrigem);
		movimentacaoConta.setContaDestino(contaDestino);
		movimentacaoConta.setDescricao(salvarMovimentacaoContaDTO.getDescricao());
		movimentacaoConta.setUsuarioCadastro(usuario);
		movimentacaoConta.setValor(salvarMovimentacaoContaDTO.getValorAsBigDecimal());
		salvarMovimentacaoConta(movimentacaoConta);

		salvarMovimentacaoContaOrigem(contaOrigem, usuario, calendar, movimentacaoConta);
		salvarMovimentacaoContaDestino(contaDestino, usuario, calendar, movimentacaoConta);

		return movimentacaoConta;
	}

	private void salvarMovimentacaoContaOrigem(Conta contaOrigem, Usuario usuario, Calendar calendar,
			MovimentacaoConta movimentacaoConta) {
		final BigDecimal valorMovimentacaoConta = movimentacaoConta.getValor();
		BigDecimal valorAtualConta = contaOrigem.getSaldo();
		valorAtualConta = valorAtualConta.subtract(valorMovimentacaoConta);

		contaOrigem.setSaldoAlterior(contaOrigem.getSaldo());
		contaOrigem.setSaldo(valorAtualConta);
		contaService.alterarConta(contaOrigem);

		LancamentoContaMovimentacao lancamento = new LancamentoContaMovimentacao();
		lancamento.setDescricao("Movimentacao de conta de saida: " + movimentacaoConta.getId());
		lancamento.setConta(contaOrigem);
		lancamento.setDataCadastro(calendar);
		lancamento.setValorLancamento(valorMovimentacaoConta);
		lancamento.setSaldoAtual(valorAtualConta);
		lancamento.setMovimentacaoConta(movimentacaoConta);
		lancamento.setUsuario(usuario);
		lancamento.setTipoLancamentoConta(TipoLancamentoConta.SAIDA);
		lancamentoContaMovimentacaoService.salvarLancamentoContaMovimentacao(lancamento);
	}

	private void salvarMovimentacaoContaDestino(Conta contaDestino, Usuario usuario, Calendar calendar,
			MovimentacaoConta movimentacaoConta) {
		final BigDecimal valorMovimentacaoConta = movimentacaoConta.getValor();
		BigDecimal valorAtualConta = contaDestino.getSaldo();
		valorAtualConta = valorAtualConta.add(valorMovimentacaoConta);

		contaDestino.setSaldoAlterior(contaDestino.getSaldo());
		contaDestino.setSaldo(valorAtualConta);
		contaService.alterarConta(contaDestino);

		LancamentoContaMovimentacao lancamento = new LancamentoContaMovimentacao();
		lancamento.setDescricao("Movimentacao de conta de entrada: " + movimentacaoConta.getId());
		lancamento.setConta(contaDestino);
		lancamento.setDataCadastro(calendar);
		lancamento.setValorLancamento(valorMovimentacaoConta);
		lancamento.setSaldoAtual(valorAtualConta);
		lancamento.setMovimentacaoConta(movimentacaoConta);
		lancamento.setUsuario(usuario);
		lancamento.setTipoLancamentoConta(TipoLancamentoConta.ENTRADA);
		lancamentoContaMovimentacaoService.salvarLancamentoContaMovimentacao(lancamento);
	}

}
