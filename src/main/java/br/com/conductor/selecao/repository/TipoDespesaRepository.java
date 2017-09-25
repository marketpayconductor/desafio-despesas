package br.com.conductor.selecao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.conductor.selecao.model.TipoDespesa;

public interface TipoDespesaRepository extends JpaRepository<TipoDespesa, Long> {

	TipoDespesa findByDescricao(String descricao);
	
}
