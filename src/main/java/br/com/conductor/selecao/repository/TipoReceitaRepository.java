package br.com.conductor.selecao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.conductor.selecao.model.TipoReceita;

public interface TipoReceitaRepository extends JpaRepository<TipoReceita, Long> {

	TipoReceita findByDescricao(String descricao);
	
}
