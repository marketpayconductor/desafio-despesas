package br.com.conductor.selecao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.conductor.selecao.model.TipoDespesa;
import br.com.conductor.selecao.repository.TipoDespesaRepository;

@Service("tipoDespesaService")
@Transactional
public class TipoDespesaServiceImpl implements TipoDespesaService {
	
	@Autowired
	private TipoDespesaRepository tipoDespesaRepository;

	@Override
	public TipoDespesa pesquisarTipoDespesaPorId(Long id) {
		return tipoDespesaRepository.findOne(id);
	}

	@Override
	public void salvarTipoDespesa(TipoDespesa tipoDespesa) {
		tipoDespesaRepository.save(tipoDespesa);		
	}

	@Override
	public void alterarTipoDespesa(TipoDespesa tipoDespesa) {
		salvarTipoDespesa(tipoDespesa);		
	}

	@Override
	public List<TipoDespesa> listarTipoDespesas() {
		return tipoDespesaRepository.findAll();
	}

	@Override
	public boolean verificarTipoDespesaExiste(TipoDespesa tipoDespesa) {		
		return tipoDespesaRepository.findByDescricao(tipoDespesa.getDescricao()) != null;
	}

}
