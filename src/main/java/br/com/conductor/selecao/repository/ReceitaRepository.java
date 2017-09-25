package br.com.conductor.selecao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.conductor.selecao.model.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

}
