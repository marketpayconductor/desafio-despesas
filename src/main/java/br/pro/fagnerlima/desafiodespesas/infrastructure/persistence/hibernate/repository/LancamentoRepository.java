package br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.pro.fagnerlima.desafiodespesas.domain.model.conta.entity.Conta;
import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.entity.Lancamento;

public interface LancamentoRepository extends BaseRepository<Lancamento, Long> {

    public Page<Lancamento> findByConta(Conta conta, Pageable pageable);
}
