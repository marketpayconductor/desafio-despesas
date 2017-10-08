package com.jarddel.desafio.api.infrastructure.persistence.hibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jarddel.desafio.api.domain.model.conta.Conta;
import com.jarddel.desafio.api.domain.model.lancamento.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    @Query("SELECT l FROM Lancamento l WHERE l.contaDestino = ?1 OR l.contaOrigem = ?1")
    List<Lancamento> buscarHistorico(Conta conta);

}
