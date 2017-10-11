package br.pro.fagnerlima.desafiodespesas.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.entity.Lancamento;
import br.pro.fagnerlima.desafiodespesas.domain.service.LancamentoService;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository.BaseRepository;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository.LancamentoRepository;

@Service
public class LancamentoServiceImpl extends BaseServiceImpl<Lancamento, Long> implements LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Override
    protected BaseRepository<Lancamento, Long> getRepository() {
        return lancamentoRepository;
    }
}
