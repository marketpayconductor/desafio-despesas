package br.pro.fagnerlima.desafiodespesas.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.pro.fagnerlima.desafiodespesas.domain.model.conta.entity.Conta;
import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.entity.Lancamento;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.CadastroTransferenciaTO;

public interface LancamentoService extends BaseService<Lancamento, Long> {

    /**
     * Retorna os Lançamentos pertencentes à Conta.
     * 
     * @param conta
     * @return Os lançamentos encontrados
     */
    public Page<Lancamento> findByConta(Conta conta, Pageable pageable);

    /**
     * Credita um lançamento.
     * 
     * @param lancamento
     * @return O lançamento creditado
     */
    public Lancamento creditar(Lancamento lancamento);

    /**
     * Debita um lançamento.
     * 
     * @param lancamento
     * @return O lançamento debitado
     * @throws Exception
     */
    public Lancamento debitar(Lancamento lancamento) throws Exception;

    /**
     * Realiza transferências entre contas.
     * 
     * @param lancamento
     * @param contaOrigem
     * @return O lançamento debitado
     * @throws Exception
     */
    public Lancamento transferir(CadastroTransferenciaTO lancamento, Conta contaOrigem) throws Exception;
}
