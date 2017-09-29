package br.com.conductor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.conductor.bean.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
	
	Conta findByNumeroConta(Long numeroConta);

}
