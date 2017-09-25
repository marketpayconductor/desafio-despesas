package br.com.conductor.selecao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.conductor.selecao.model.LancamentoContaDespesa;
import br.com.conductor.selecao.repository.LancamentoContaDespesaRepository;

@Service("lancamentoContaDespesaService")
@Transactional
public class LancamentoContaDespesaServiceImpl implements LancamentoContaDespesaService {
	
	@Autowired
	private LancamentoContaDespesaRepository lancamentoContaDespesaRepository;

	@Override
	public LancamentoContaDespesa pesquisarLancamentoContaDespesaPorId(Long id) {
		return lancamentoContaDespesaRepository.findOne(id);
	}

	@Override
	public void salvarLancamentoContaDespesa(LancamentoContaDespesa lancamentoContaDespesa) {
		lancamentoContaDespesaRepository.save(lancamentoContaDespesa);
	}

	@Override
	public void alterarLancamentoContaDespesa(LancamentoContaDespesa lancamentoContaDespesa) {
		salvarLancamentoContaDespesa(lancamentoContaDespesa);
	}

	@Override
	public List<LancamentoContaDespesa> listarLancamentoContaDespesas() {
		return lancamentoContaDespesaRepository.findAll();
	}
	
}
