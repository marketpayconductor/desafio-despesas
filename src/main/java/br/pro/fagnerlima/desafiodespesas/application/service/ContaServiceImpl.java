package br.pro.fagnerlima.desafiodespesas.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.pro.fagnerlima.desafiodespesas.application.service.exception.ContaNaoEncontradaException;
import br.pro.fagnerlima.desafiodespesas.domain.model.conta.entity.Conta;
import br.pro.fagnerlima.desafiodespesas.domain.service.ContaService;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository.BaseRepository;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository.ContaRepository;

@Service
public class ContaServiceImpl extends BaseServiceImpl<Conta, Long> implements ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public Conta findOne(Long id) throws EmptyResultDataAccessException {
        try {
            return super.findOne(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ContaNaoEncontradaException();
        }
    }

    @Override
    public void updateAtivo(Long id) {
        Conta conta = findOne(id);
        conta.changeAtivo();

        contaRepository.save(conta);
    }

    @Override
    protected BaseRepository<Conta, Long> getRepository() {
        return contaRepository;
    }
}
