package br.com.conductor.selecao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.conductor.selecao.model.MovimentacaoConta;

public interface MovimentacaoContaRepository extends JpaRepository<MovimentacaoConta, Long> {

}
