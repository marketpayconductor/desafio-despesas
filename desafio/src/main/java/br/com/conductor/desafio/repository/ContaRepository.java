package br.com.conductor.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.conductor.desafio.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

	Conta findByNumeroConta(@Param(value = "numeroConta") String numeroConta);

}
