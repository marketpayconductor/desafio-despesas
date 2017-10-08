package com.jarddel.desafio.api.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jarddel.desafio.api.application.service.exception.ContaInexistenteException;
import com.jarddel.desafio.api.domain.exception.LancamentoInvalidoException;
import com.jarddel.desafio.api.domain.model.conta.Conta;
import com.jarddel.desafio.api.domain.model.lancamento.Despesa;
import com.jarddel.desafio.api.domain.model.lancamento.Lancamento;
import com.jarddel.desafio.api.domain.model.lancamento.Receita;
import com.jarddel.desafio.api.domain.model.lancamento.Transferencia;
import com.jarddel.desafio.api.domain.service.LancamentoServiceInterface;
import com.jarddel.desafio.api.infrastructure.persistence.hibernate.repository.LancamentoRepository;
import com.jarddel.desafio.api.presentation.dto.LancamentoDTO;

@Service
public class LancamentoService extends BaseService<Lancamento> implements LancamentoServiceInterface {

    @Autowired
    protected LancamentoRepository lancamentoRepository;

    @Autowired
    private ContaService contaService;

    @Override
    protected LancamentoRepository getRepository() {
        return lancamentoRepository;
    }

    @Cacheable("meuHistorico")
    public List<Lancamento> meuHistorico() {
        Conta conta = contaService.buscar(obterMinhaConta().getId());
        List<Lancamento> lancamentos = lancamentoRepository.buscarHistorico(conta);
        return lancamentos;
    }

    @Cacheable("historico")
    public List<Lancamento> historico(Long id) {
        List<Lancamento> lancamentos = lancamentoRepository.buscarHistorico(contaService.buscar(id));
        return lancamentos;
    }

    @Transactional(rollbackFor = LancamentoInvalidoException.class)
    public Receita creditar(LancamentoDTO lancamento) {
        Conta contaDestino = contaService.buscar(obterMinhaConta().getId());
        contaDestino.creditar(lancamento.getValor());
        return (Receita) salvar(new Receita(lancamento.getValor(), contaDestino));
    }

    @Transactional(rollbackFor = LancamentoInvalidoException.class)
    public Despesa debitar(LancamentoDTO lancamento) {
        Conta contaDestino = contaService.buscar(obterMinhaConta().getId());
        contaDestino.debitar(lancamento.getValor());
        return (Despesa) salvar(new Despesa(lancamento.getValor(), contaDestino));
    }

    @Transactional(rollbackFor = LancamentoInvalidoException.class)
    public Transferencia transferir(LancamentoDTO lancamento) {
        Conta contaOrigem = contaService.buscar(obterMinhaConta().getId());
        contaOrigem.debitar(lancamento.getValor());
        Conta contaDestino = contaService.buscar(lancamento.getIdContaDestino());
        contaDestino.creditar(lancamento.getValor());
        return (Transferencia) salvar(new Transferencia(lancamento.getValor(), contaDestino, contaOrigem));
    }

    private Conta obterMinhaConta() {
        Conta conta = userDetailsService.obterUsuarioLogado().getConta();

        if (conta == null) {
            throw new ContaInexistenteException();
        }

        return conta;
    }

}
