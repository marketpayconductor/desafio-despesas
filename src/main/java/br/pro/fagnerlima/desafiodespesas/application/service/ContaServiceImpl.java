package br.pro.fagnerlima.desafiodespesas.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pro.fagnerlima.desafiodespesas.domain.model.conta.entity.Conta;
import br.pro.fagnerlima.desafiodespesas.domain.service.ContaService;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository.BaseRepository;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository.ContaRepository;

@Service
public class ContaServiceImpl extends BaseServiceImpl<Conta, Long> implements ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Override
    protected BaseRepository<Conta, Long> getRepository() {
        return contaRepository;
    }
}
