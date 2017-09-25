package br.com.conductor.selecao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.conductor.selecao.model.LancamentoConta;
import br.com.conductor.selecao.repository.LancamentoContaRepository;

@Service("lancamentoContaService")
@Transactional
public class LancamentoContaServiceImpl implements LancamentoContaService {

	@Autowired
	private LancamentoContaRepository lancamentoContaRepository;

	@Override
	public LancamentoConta pesquisarLancamentoContaPorId(Long id) {
		return lancamentoContaRepository.findOne(id);
	}

	@Override
	public void salvarLancamentoConta(LancamentoConta lancamentoConta) {
		lancamentoContaRepository.save(lancamentoConta);		
	}

	@Override
	public void alterarLancamentoConta(LancamentoConta lancamentoConta) {
		salvarLancamentoConta(lancamentoConta);		
	}

	@Override
	public List<LancamentoConta> listarLancamentoContas() {
		return lancamentoContaRepository.findAll();
	}

}
