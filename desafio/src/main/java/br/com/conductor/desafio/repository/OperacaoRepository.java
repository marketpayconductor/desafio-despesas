package br.com.conductor.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.conductor.desafio.model.Conta;
import br.com.conductor.desafio.model.Operacao;

public interface OperacaoRepository extends JpaRepository<Operacao, Long> {

	List<Operacao> findByConta(Conta conta);
}
