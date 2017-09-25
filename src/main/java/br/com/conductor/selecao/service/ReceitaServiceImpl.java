package br.com.conductor.selecao.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.conductor.selecao.dto.CancelarReceitaDTO;
import br.com.conductor.selecao.dto.PagarReceitaDTO;
import br.com.conductor.selecao.dto.SalvarAlterarReceitaDTO;
import br.com.conductor.selecao.exception.EntidadeNaoEncontrada;
import br.com.conductor.selecao.exception.ParametroRequestInvalidoException;
import br.com.conductor.selecao.exception.ReceitaNaoAbertaException;
import br.com.conductor.selecao.model.Conta;
import br.com.conductor.selecao.model.LancamentoContaReceita;
import br.com.conductor.selecao.model.Receita;
import br.com.conductor.selecao.model.StatusReceita;
import br.com.conductor.selecao.model.TipoLancamentoConta;
import br.com.conductor.selecao.model.TipoReceita;
import br.com.conductor.selecao.model.Usuario;
import br.com.conductor.selecao.repository.ReceitaRepository;

@Service("receitaService")
@Transactional
public class ReceitaServiceImpl implements ReceitaService {

	@Autowired
	private ReceitaRepository receitaRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ContaService contaService;

	@Autowired
	private LancamentoContaReceitaService lancamentoContaReceitaService;
	
	@Autowired
	private TipoReceitaService tipoReceitaService;

	@Override
	public Receita pesquisarReceitaPorId(Long id) {
		return receitaRepository.findOne(id);
	}

	@Override
	public void salvarReceita(Receita receita) {
		receitaRepository.save(receita);
	}

	@Override
	public void alterarReceita(Receita receita) {
		salvarReceita(receita);
	}

	@Override
	public List<Receita> listarReceitas() {
		return receitaRepository.findAll();
	}

	@Override
	public boolean verificarReceitaExiste(Receita receita) {
		return pesquisarReceitaPorId(receita.getId()) != null;
	}

	@Override
	public Receita pagarReceita(Long idReceita, PagarReceitaDTO receitaDTO)
			throws EntidadeNaoEncontrada, ReceitaNaoAbertaException {
		Receita receita = pesquisarReceitaPorId(idReceita);
		if (receita == null) {
			throw new EntidadeNaoEncontrada(Receita.class);

		} else if (!StatusReceita.ABERTO.equals(receita.getStatus())) {
			throw new ReceitaNaoAbertaException();
		}

		Conta conta = contaService.pesquisarContaPorId(receitaDTO.getConta());
		if (conta == null) {
			throw new EntidadeNaoEncontrada(Conta.class);
		}

		Usuario usuario = usuarioService.pesquisarUsuarioPorId(receitaDTO.getUsuarioPagamento());
		if (usuario == null) {
			throw new EntidadeNaoEncontrada(Usuario.class);
		}

		Calendar calendar = Calendar.getInstance();

		receita.setDataRecebimento(calendar);
		receita.setStatus(StatusReceita.RECEBIDO);
		receita.setUsuarioRecebimento(usuario);
		alterarReceita(receita);

		BigDecimal valorReceitaEntrada = receita.getValor();
		BigDecimal valorAtualConta = conta.getSaldo();
		valorAtualConta = valorAtualConta.add(valorReceitaEntrada);

		conta.setSaldoAlterior(conta.getSaldo());
		conta.setSaldo(valorAtualConta);
		contaService.alterarConta(conta);

		LancamentoContaReceita lancamento = new LancamentoContaReceita();
		lancamento.setDescricao("Recebimento da receita: " + receita.getId());
		lancamento.setConta(conta);
		lancamento.setDataCadastro(calendar);
		lancamento.setValorLancamento(valorReceitaEntrada);
		lancamento.setSaldoAtual(valorAtualConta);
		lancamento.setReceita(receita);
		lancamento.setUsuario(usuario);
		lancamento.setTipoLancamentoConta(TipoLancamentoConta.ENTRADA);
		lancamentoContaReceitaService.salvarLancamentoContaReceita(lancamento);

		return receita;

	}

	@Override
	public void cancelarReceita(Long idReceita, CancelarReceitaDTO cancelarReceitaDTO)
			throws EntidadeNaoEncontrada, ReceitaNaoAbertaException {
		Receita receita = pesquisarReceitaPorId(idReceita);
		if (receita == null) {
			throw new EntidadeNaoEncontrada(Receita.class);
		} else if (!StatusReceita.ABERTO.equals(receita.getStatus())) {
			throw new ReceitaNaoAbertaException();
		}

		Usuario usuario = usuarioService.pesquisarUsuarioPorId(cancelarReceitaDTO.getUsuarioCancelamento());
		if (usuario == null) {
			throw new EntidadeNaoEncontrada(Usuario.class);
		}

		Calendar calendar = Calendar.getInstance();

		receita.setDataCancelamento(calendar);
		receita.setStatus(StatusReceita.CANCELADO);
		receita.setUsuarioCancelamento(usuario);

		alterarReceita(receita);

	}

	@Override
	public Receita alterarReceita(long id, SalvarAlterarReceitaDTO receitaDTO)
			throws ParametroRequestInvalidoException, ReceitaNaoAbertaException, EntidadeNaoEncontrada {
		Receita receita = pesquisarReceitaPorId(id);
		if (receita == null) {
			throw new EntidadeNaoEncontrada(Receita.class);
		} else if (!StatusReceita.ABERTO.equals(receita.getStatus())) {
			throw new ReceitaNaoAbertaException();
		}
		
		TipoReceita tipoReceita = tipoReceitaService.pesquisarTipoReceitaPorId(receitaDTO.getTipoReceita());
		if (tipoReceita == null) {
			throw new EntidadeNaoEncontrada(TipoReceita.class);
		}
		
		Usuario usuario = usuarioService.pesquisarUsuarioPorId(receitaDTO.getUsuarioCadastro());
		if (usuario == null) {
			throw new EntidadeNaoEncontrada(Usuario.class);
		}

		receita.setDescricao(receitaDTO.getDescricao());
		receita.setValor(receitaDTO.getValorAsBigDecimal());
		receita.setTipoReceita(tipoReceita);
		receita.setUsuarioCadastro(usuario);
		
		alterarReceita(receita);
		
		return receita;
	}
	
	@Override
	public Receita salvarReceita(SalvarAlterarReceitaDTO receitaDTO)
			throws ParametroRequestInvalidoException, EntidadeNaoEncontrada {		
		TipoReceita tipoReceita = tipoReceitaService.pesquisarTipoReceitaPorId(receitaDTO.getTipoReceita());
		if (tipoReceita == null) {
			throw new EntidadeNaoEncontrada(TipoReceita.class);
		}
		
		Usuario usuario = usuarioService.pesquisarUsuarioPorId(receitaDTO.getUsuarioCadastro());
		if (usuario == null) {
			throw new EntidadeNaoEncontrada(Usuario.class);
		}

		Receita receita = new Receita();
		receita.setDataCadastro(Calendar.getInstance());
		receita.setStatus(StatusReceita.ABERTO);
		receita.setDescricao(receitaDTO.getDescricao());
		receita.setValor(receitaDTO.getValorAsBigDecimal());
		receita.setTipoReceita(tipoReceita);
		receita.setUsuarioCadastro(usuario);
		
		salvarReceita(receita);
		
		return receita;
	}

}
