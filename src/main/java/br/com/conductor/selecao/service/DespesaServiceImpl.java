package br.com.conductor.selecao.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.conductor.selecao.dto.CancelarDespesaDTO;
import br.com.conductor.selecao.dto.PagarDespesaDTO;
import br.com.conductor.selecao.dto.SalvarAlterarDespesaDTO;
import br.com.conductor.selecao.exception.DespesaNaoAbertaException;
import br.com.conductor.selecao.exception.EntidadeNaoEncontrada;
import br.com.conductor.selecao.exception.ParametroRequestInvalidoException;
import br.com.conductor.selecao.model.Conta;
import br.com.conductor.selecao.model.Despesa;
import br.com.conductor.selecao.model.LancamentoContaDespesa;
import br.com.conductor.selecao.model.StatusDespesa;
import br.com.conductor.selecao.model.TipoDespesa;
import br.com.conductor.selecao.model.TipoLancamentoConta;
import br.com.conductor.selecao.model.Usuario;
import br.com.conductor.selecao.repository.DespesaRepository;

@Service("despesaService")
@Transactional
public class DespesaServiceImpl implements DespesaService {

	@Autowired
	private DespesaRepository despesaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TipoDespesaService tipoDespesaService;
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private LancamentoContaDespesaService lancamentoContaDespesaService;

	@Override
	public Despesa pesquisarDespesaPorId(Long id) {
		return despesaRepository.findOne(id);
	}

	@Override
	public void salvarDespesa(Despesa Despesa) {
		despesaRepository.save(Despesa);
	}

	@Override
	public void alterarDespesa(Despesa Despesa) {
		salvarDespesa(Despesa);
	}

	@Override
	public List<Despesa> listarDespesas() {
		return despesaRepository.findAll();
	}

	@Override
	public boolean verificarDespesaExiste(Despesa Despesa) {
		return pesquisarDespesaPorId(Despesa.getId()) != null;
	}

	@Override
	public Despesa pagarDespesa(Long idDespesa, PagarDespesaDTO despesaDTO) throws EntidadeNaoEncontrada, DespesaNaoAbertaException {
		Despesa Despesa = pesquisarDespesaPorId(idDespesa);
		if (Despesa == null) {
			throw new EntidadeNaoEncontrada(Despesa.class);

		} else if(!StatusDespesa.ABERTO.equals(Despesa.getStatus())) {
			throw new DespesaNaoAbertaException();
		}
		
		Conta conta = contaService.pesquisarContaPorId(despesaDTO.getConta());
		if (conta == null) {
			throw new EntidadeNaoEncontrada(Conta.class);
		}
		
		Usuario usuario = usuarioService.pesquisarUsuarioPorId(despesaDTO.getUsuarioPagamento());
		if (usuario == null) {
			throw new EntidadeNaoEncontrada(Usuario.class);
		}
		
		Calendar calendar = Calendar.getInstance();
		
		Despesa.setDataPagamento(calendar);
		Despesa.setStatus(StatusDespesa.PAGO);
		Despesa.setUsuarioPagamento(usuario);
		alterarDespesa(Despesa);
		
		BigDecimal valorDespesaEntrada = Despesa.getValor();
		BigDecimal valorAtualConta = conta.getSaldo();		
		valorAtualConta = valorAtualConta.subtract(valorDespesaEntrada);
		
		conta.setSaldoAlterior(conta.getSaldo());
		conta.setSaldo(valorAtualConta);
		contaService.alterarConta(conta);
		
		LancamentoContaDespesa lancamento = new LancamentoContaDespesa();
		lancamento.setDescricao("Pagmanento da despesa: " + Despesa.getId());
		lancamento.setConta(conta);
		lancamento.setDataCadastro(calendar);
		lancamento.setValorLancamento(valorDespesaEntrada);
		lancamento.setSaldoAtual(valorAtualConta);
		lancamento.setDespesa(Despesa);
		lancamento.setUsuario(usuario);
		lancamento.setTipoLancamentoConta(TipoLancamentoConta.SAIDA);
		lancamentoContaDespesaService.salvarLancamentoContaDespesa(lancamento);
		
		return Despesa;
		
	}

	@Override
	public void cancelarDespesa(Long idDespesa, CancelarDespesaDTO cancelarDespesaDTO) throws EntidadeNaoEncontrada, DespesaNaoAbertaException {
		Despesa Despesa = pesquisarDespesaPorId(idDespesa);
		if (Despesa == null) {
			throw new EntidadeNaoEncontrada(Despesa.class);
		} else if(!StatusDespesa.ABERTO.equals(Despesa.getStatus())) {
			throw new DespesaNaoAbertaException();
		}
		
		Usuario usuario = usuarioService.pesquisarUsuarioPorId(cancelarDespesaDTO.getUsuarioCancelamento());
		if (usuario == null) {
			throw new EntidadeNaoEncontrada(Usuario.class);
		}
		
		Calendar calendar = Calendar.getInstance();
		
		Despesa.setDataCancelamento(calendar);
		Despesa.setStatus(StatusDespesa.CANCELADO);
		Despesa.setUsuarioCancelamento(usuario);
		
		alterarDespesa(Despesa);
		
	}

	@Override
	public Despesa alterarDespesa(long idDespesa, SalvarAlterarDespesaDTO despesaDTO) throws ParametroRequestInvalidoException, DespesaNaoAbertaException, EntidadeNaoEncontrada {
		Despesa despesa = pesquisarDespesaPorId(idDespesa);
		if (despesa == null) {
			throw new EntidadeNaoEncontrada(Despesa.class);
		} else if (!StatusDespesa.ABERTO.equals(despesa.getStatus())) {
			throw new DespesaNaoAbertaException();
		}
		
		Usuario usuario = usuarioService.pesquisarUsuarioPorId(despesaDTO.getUsuarioCadastro());
		if (usuario == null) {
			throw new EntidadeNaoEncontrada(Usuario.class);
		}
		
		TipoDespesa tipoDespesa = tipoDespesaService.pesquisarTipoDespesaPorId(despesaDTO.getTipoDespesa());
		if (tipoDespesa == null) {
			throw new EntidadeNaoEncontrada(TipoDespesa.class);
		}

		despesa.setDescricao(despesaDTO.getDescricao());		
		despesa.setDataVencimento(despesaDTO.getDataVencimentoAsCalendar());
		despesa.setValor(despesaDTO.getValorAsBigDecimal());
		despesa.setTipoDespesa(tipoDespesa);
		despesa.setUsuarioCadastro(usuario);

		alterarDespesa(despesa);	
		
		return despesa;
	}

	@Override
	public Despesa salvarDespesa(SalvarAlterarDespesaDTO despesaDTO) throws EntidadeNaoEncontrada, ParametroRequestInvalidoException {
		Usuario usuario = usuarioService.pesquisarUsuarioPorId(despesaDTO.getUsuarioCadastro());
		if (usuario == null) {
			throw new EntidadeNaoEncontrada(Usuario.class);
		}
		
		TipoDespesa tipoDespesa = tipoDespesaService.pesquisarTipoDespesaPorId(despesaDTO.getTipoDespesa());
		if (tipoDespesa == null) {
			throw new EntidadeNaoEncontrada(TipoDespesa.class);
		}

		Despesa despesa = new Despesa();
		despesa.setDescricao(despesaDTO.getDescricao());
		despesa.setDataEmissao(Calendar.getInstance());
		despesa.setDataVencimento(despesaDTO.getDataVencimentoAsCalendar());
		despesa.setValor(despesaDTO.getValorAsBigDecimal());
		despesa.setTipoDespesa(tipoDespesa);
		despesa.setUsuarioCadastro(usuario);
		despesa.setStatus(StatusDespesa.ABERTO);

		salvarDespesa(despesa);	
		
		return despesa;
	}

}
