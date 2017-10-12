package br.pro.fagnerlima.desafiodespesas.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.pro.fagnerlima.desafiodespesas.application.service.exception.LancamentoInvalidoException;
import br.pro.fagnerlima.desafiodespesas.domain.model.conta.entity.Conta;
import br.pro.fagnerlima.desafiodespesas.domain.model.exception.SaldoInsuficienteException;
import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.entity.Lancamento;
import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.enumeration.TipoLancamento;
import br.pro.fagnerlima.desafiodespesas.domain.service.ContaService;
import br.pro.fagnerlima.desafiodespesas.domain.service.LancamentoService;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository.BaseRepository;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository.LancamentoRepository;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.CadastroTransferenciaTO;

@Service
public class LancamentoServiceImpl extends BaseServiceImpl<Lancamento, Long> implements LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ContaService contaService;

    @Override
    public Page<Lancamento> findByConta(Conta conta, Pageable pageable) {
        return lancamentoRepository.findByConta(conta, pageable);
    }

    @Override
    @Transactional(rollbackFor = LancamentoInvalidoException.class)
    public Lancamento creditar(Lancamento lancamento) {
        Conta conta = lancamento.getConta();
        conta.creditar(lancamento.getValor());
        contaService.save(conta);

        return lancamento = save(lancamento);
    }

    @Override
    @Transactional(rollbackFor = LancamentoInvalidoException.class)
    public Lancamento debitar(Lancamento lancamento) throws SaldoInsuficienteException {
        Conta conta = lancamento.getConta();
        conta.debitar(lancamento.getValor());
        contaService.save(conta);

        return save(lancamento);
    }

    @Override
    @Transactional(rollbackFor = LancamentoInvalidoException.class)
    public Lancamento transferir(CadastroTransferenciaTO cadastroTransferenciaTO, Conta contaOrigem)
            throws SaldoInsuficienteException {
        Conta contaDestino = contaService.findOne(cadastroTransferenciaTO.getContaDestino());

        Lancamento lancamentoDebitado = debitar(new Lancamento(TipoLancamento.DESPESA, contaOrigem,
                cadastroTransferenciaTO.getValor(), cadastroTransferenciaTO.getDescricao()));
        creditar(new Lancamento(TipoLancamento.RECEITA, contaDestino,
                cadastroTransferenciaTO.getValor(), cadastroTransferenciaTO.getDescricao()));

        return lancamentoDebitado;
    }

    @Override
    protected BaseRepository<Lancamento, Long> getRepository() {
        return lancamentoRepository;
    }
}
