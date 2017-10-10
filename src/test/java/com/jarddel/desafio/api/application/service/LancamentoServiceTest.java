package com.jarddel.desafio.api.application.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.jarddel.desafio.api.domain.exception.SaldoInsuficienteException;
import com.jarddel.desafio.api.domain.model.conta.Conta;
import com.jarddel.desafio.api.domain.model.lancamento.Transferencia;
import com.jarddel.desafio.api.infrastructure.service.ApplicationUserDetailsService;
import com.jarddel.desafio.api.presentation.dto.LancamentoDTO;

@RunWith(SpringRunner.class)
@RestClientTest({ UsuarioService.class, LancamentoService.class, ContaService.class })
@DataJpaTest
public class LancamentoServiceTest {

    @Before
    public void setUp() throws Exception {
        Mockito.when(appUserService.obterUsuarioLogado()).thenReturn(usuarioService.buscar(1L));
    }

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LancamentoService lancamentoService;

    @MockBean
    public ApplicationUserDetailsService appUserService;

    @Test
    public void creditarContaZerada() {
        BigDecimal valor = new BigDecimal("50.00");
        lancamentoService.creditar(new LancamentoDTO(valor, null, null));
        Conta conta = usuarioService.buscar(1L).getConta();

        assertEquals(valor, conta.getSaldo());
    }

    @Test
    public void debitarContaComSaldo() {
        lancamentoService.creditar(new LancamentoDTO(new BigDecimal("100.00")));
        lancamentoService.debitar(new LancamentoDTO(new BigDecimal("100.00")));

        assertEquals(new BigDecimal("0.00"), usuarioService.buscar(1L).getConta().getSaldo());
    }

    @Test(expected = SaldoInsuficienteException.class)
    public void debitarContaComSaldoInsuficiente() {
        lancamentoService.creditar(new LancamentoDTO(new BigDecimal("100.00")));
        lancamentoService.debitar(new LancamentoDTO(new BigDecimal("100.01")));
    }

    @Test
    public void transferirContaComSaldo() {
        Long idContaDestino = new Long("2");
        lancamentoService.creditar(new LancamentoDTO(new BigDecimal("100.00")));
        LancamentoDTO lancamento = new LancamentoDTO(new BigDecimal("100.00"), null, idContaDestino);
        Transferencia transferencia = lancamentoService.transferir(lancamento);

        assertEquals(new BigDecimal("0.00"), transferencia.getContaOrigem().getSaldo());
        assertEquals(new BigDecimal("100.00"), transferencia.getContaDestino().getSaldo());
    }

    @Test(expected = SaldoInsuficienteException.class)
    public void transferirContaSaldoInsuficiente() {
        Long idContaDestino = new Long("2");
        lancamentoService.creditar(new LancamentoDTO(new BigDecimal("100.00")));
        lancamentoService.transferir(new LancamentoDTO(new BigDecimal("100.01"), null, idContaDestino));
    }
}
