package com.jarddel.desafio.api.infrastructure.persistence.hibernate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jarddel.desafio.api.domain.model.conta.Conta;
import com.jarddel.desafio.api.domain.model.lancamento.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    @Query("SELECT l FROM Lancamento l WHERE l.contaDestino = ?1 OR l.contaOrigem = ?1")
    Page<Lancamento> buscarHistorico(Conta conta, Pageable pageable);

}
