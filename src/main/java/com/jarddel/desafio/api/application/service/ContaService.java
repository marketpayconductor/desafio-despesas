package com.jarddel.desafio.api.application.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jarddel.desafio.api.application.service.exception.ContaInexistenteException;
import com.jarddel.desafio.api.application.service.exception.InformacaoNaoEncontradaException;
import com.jarddel.desafio.api.domain.model.conta.Conta;
import com.jarddel.desafio.api.domain.service.ContaServiceInterface;
import com.jarddel.desafio.api.infrastructure.persistence.hibernate.repository.ContaRepository;

@Service
public class ContaService extends BaseService<Conta> implements ContaServiceInterface {

    @Autowired
    protected ContaRepository contaRepository;

    @Override
    protected ContaRepository getRepository() {
        return contaRepository;
    }

    public Conta cadastrarMe(Conta conta) {
        conta.setUsuario(userDetailsService.obterUsuarioLogado());
        return salvar(conta);
    }

    public Conta atualizarMe(Conta conta) {
        Conta contaLogada = userDetailsService.obterUsuarioLogado().getConta();
        verificarContaExiste(contaLogada);
        return atualizar(contaLogada.getId(), conta);
    }

    public BigDecimal obterSaldo(Long id) {
        try {
            Conta conta = buscar(id);
            return conta.getSaldo();
        } catch (InformacaoNaoEncontradaException ex) {
            throw new ContaInexistenteException();
        }
    }

    public BigDecimal obterMeuSaldo() {
        Conta conta = userDetailsService.obterUsuarioLogado().getConta();
        verificarContaExiste(conta);
        return conta.getSaldo();
    }

    public Conta atualizarPropriedadeAtivo(Boolean ativo) {
        Conta conta = userDetailsService.obterUsuarioLogado().getConta();
        verificarContaExiste(conta);
        conta.setAtivo(ativo);
        return salvar(conta);
    }

    private void verificarContaExiste(Conta conta) {
        if (conta == null) {
            throw new ContaInexistenteException();
        }
    }
}
