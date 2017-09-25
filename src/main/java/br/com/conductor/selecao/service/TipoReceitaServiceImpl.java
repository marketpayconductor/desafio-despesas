package br.com.conductor.selecao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.conductor.selecao.model.TipoReceita;
import br.com.conductor.selecao.repository.TipoReceitaRepository;

@Service("tipoReceitaService")
@Transactional
public class TipoReceitaServiceImpl implements TipoReceitaService {
	
	@Autowired
	private TipoReceitaRepository tipoReceitaRepository;

	@Override
	public TipoReceita pesquisarTipoReceitaPorId(Long id) {
		return tipoReceitaRepository.findOne(id);
	}

	@Override
	public void salvarTipoReceita(TipoReceita tipoReceita) {
		tipoReceitaRepository.save(tipoReceita);		
	}

	@Override
	public void alterarTipoReceita(TipoReceita tipoReceita) {
		salvarTipoReceita(tipoReceita);		
	}

	@Override
	public List<TipoReceita> listarTipoReceitas() {
		return tipoReceitaRepository.findAll();
	}

	@Override
	public boolean verificarTipoReceitaExiste(TipoReceita tipoReceita) {		
		return tipoReceitaRepository.findByDescricao(tipoReceita.getDescricao()) != null;
	}

}
