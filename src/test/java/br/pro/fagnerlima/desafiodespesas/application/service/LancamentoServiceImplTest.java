package br.pro.fagnerlima.desafiodespesas.application.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import br.pro.fagnerlima.desafiodespesas.domain.model.conta.entity.Conta;
import br.pro.fagnerlima.desafiodespesas.domain.model.exception.SaldoInsuficienteException;
import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.entity.Lancamento;
import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.enumeration.TipoLancamento;
import br.pro.fagnerlima.desafiodespesas.domain.service.LancamentoService;
import br.pro.fagnerlima.desafiodespesas.domain.service.UsuarioService;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.CadastroTransferenciaTO;

@RunWith(SpringRunner.class)
@Profile("test")
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LancamentoServiceImplTest {

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void test1Creditar() {
        Lancamento lancamento = new Lancamento(TipoLancamento.RECEITA, usuarioService.findOne(2L).getConta(),
                new BigDecimal(150), "Teste creditar");
        lancamento = lancamentoService.creditar(lancamento);

        assertTrue(0 == lancamento.getConta().getSaldo().compareTo(new BigDecimal(150)));
    }

    @Test
    public void test2Debitar() throws Exception {
        Lancamento lancamento = new Lancamento(TipoLancamento.DESPESA, usuarioService.findOne(2L).getConta(),
                new BigDecimal(50), "Teste debitar");
        lancamento = lancamentoService.debitar(lancamento);

        assertTrue(0 == lancamento.getConta().getSaldo().compareTo(new BigDecimal(100)));
    }

    @Test(expected = SaldoInsuficienteException.class)
    public void test3DebitarComSaldoInsuficiente() throws Exception {
        Lancamento lancamento = new Lancamento(TipoLancamento.DESPESA, usuarioService.findOne(2L).getConta(),
                new BigDecimal(101.10), "Teste debitar");
        lancamento = lancamentoService.debitar(lancamento);
    }

    @Test
    public void test4Transferir() throws Exception {
        Conta contaOrigem = usuarioService.findOne(2L).getConta();
        CadastroTransferenciaTO cadastroTransferenciaTO = new CadastroTransferenciaTO("Teste transferir",
                new BigDecimal(100), 3L);
        Lancamento lancamento = lancamentoService.transferir(cadastroTransferenciaTO, contaOrigem);

        assertTrue(0 == lancamento.getConta().getSaldo().compareTo(new BigDecimal(0)));
        assertTrue(0 == usuarioService.findOne(3L).getConta().getSaldo().compareTo(new BigDecimal(100)));
    }

    @Test(expected = SaldoInsuficienteException.class)
    public void test5TransferirComSaldoInsuficiente() throws Exception {
        Conta contaOrigem = usuarioService.findOne(2L).getConta();
        CadastroTransferenciaTO cadastroTransferenciaTO = new CadastroTransferenciaTO("Teste transferir",
                new BigDecimal(1), 3L);
        lancamentoService.transferir(cadastroTransferenciaTO, contaOrigem);
    }
}
