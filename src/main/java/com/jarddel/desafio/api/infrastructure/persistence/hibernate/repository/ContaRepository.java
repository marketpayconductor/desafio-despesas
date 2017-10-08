package com.jarddel.desafio.api.infrastructure.persistence.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jarddel.desafio.api.domain.model.conta.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
