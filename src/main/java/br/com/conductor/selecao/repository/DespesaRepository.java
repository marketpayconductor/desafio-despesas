package br.com.conductor.selecao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.conductor.selecao.model.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

}
