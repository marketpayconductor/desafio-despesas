package br.com.conductor.selecao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.conductor.selecao.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

	Conta findByBancoAndAgenciaAndConta(String banco, String agencia, String conta);

}
