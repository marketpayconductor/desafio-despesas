package br.com.conductor.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.conductor.desafio.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
	
	Conta findByIdConta(Integer idConta);
	
	Conta findByNumeroConta(Long numeroConta);

}
