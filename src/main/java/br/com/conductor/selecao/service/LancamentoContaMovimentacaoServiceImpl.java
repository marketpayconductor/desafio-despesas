package br.com.conductor.selecao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.conductor.selecao.model.LancamentoContaMovimentacao;
import br.com.conductor.selecao.repository.LancamentoContaMovimentacaoRepository;

@Service("lancamentoContaMovimentacaoService")
@Transactional
public class LancamentoContaMovimentacaoServiceImpl implements LancamentoContaMovimentacaoService {
	
	@Autowired
	private LancamentoContaMovimentacaoRepository lancamentoContaMovimentacaoRepository;

	@Override
	public LancamentoContaMovimentacao pesquisarLancamentoContaMovimentacaoPorId(Long id) {
		return lancamentoContaMovimentacaoRepository.findOne(id);
	}

	@Override
	public void salvarLancamentoContaMovimentacao(LancamentoContaMovimentacao lancamentoContaMovimentacao) {
		lancamentoContaMovimentacaoRepository.save(lancamentoContaMovimentacao);
	}

	@Override
	public void alterarLancamentoContaMovimentacao(LancamentoContaMovimentacao lancamentoContaMovimentacao) {
		salvarLancamentoContaMovimentacao(lancamentoContaMovimentacao);
	}

	@Override
	public List<LancamentoContaMovimentacao> listarLancamentoContaMovimentacaos() {
		return lancamentoContaMovimentacaoRepository.findAll();
	}
	
}
