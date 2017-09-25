package br.com.conductor.selecao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.conductor.selecao.model.LancamentoContaReceita;
import br.com.conductor.selecao.repository.LancamentoContaReceitaRepository;

@Service("lancamentoContaReceitaService")
@Transactional
public class LancamentoContaReceitaServiceImpl implements LancamentoContaReceitaService {
	
	@Autowired
	private LancamentoContaReceitaRepository lancamentoContaReceitaRepository;

	@Override
	public LancamentoContaReceita pesquisarLancamentoContaReceitaPorId(Long id) {
		return lancamentoContaReceitaRepository.findOne(id);
	}

	@Override
	public void salvarLancamentoContaReceita(LancamentoContaReceita lancamentoContaReceita) {
		lancamentoContaReceitaRepository.save(lancamentoContaReceita);
	}

	@Override
	public void alterarLancamentoContaReceita(LancamentoContaReceita lancamentoContaReceita) {
		salvarLancamentoContaReceita(lancamentoContaReceita);
	}

	@Override
	public List<LancamentoContaReceita> listarLancamentoContaReceitas() {
		return lancamentoContaReceitaRepository.findAll();
	}
	
}
