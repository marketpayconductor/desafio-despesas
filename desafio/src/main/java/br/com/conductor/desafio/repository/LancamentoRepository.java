package br.com.conductor.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.conductor.desafio.model.Conta;
import br.com.conductor.desafio.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	
	List<Lancamento> findByConta(Conta conta);
	
}
