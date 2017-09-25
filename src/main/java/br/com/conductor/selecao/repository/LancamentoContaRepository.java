package br.com.conductor.selecao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.conductor.selecao.model.LancamentoConta;

public interface LancamentoContaRepository extends JpaRepository<LancamentoConta, Long> {

}
