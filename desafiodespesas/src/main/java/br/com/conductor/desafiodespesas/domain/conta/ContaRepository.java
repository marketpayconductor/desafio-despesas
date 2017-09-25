package br.com.conductor.desafiodespesas.domain.conta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface ContaRepository extends JpaRepository<Conta, Long> {
	
	Conta findByNumeroConta(@Param(value = "numeroConta") String numeroConta);
	Conta findByCpf(@Param(value = "cpf") String cpf);

}
