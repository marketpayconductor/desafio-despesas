package com.jarddel.desafio.api.application.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.jarddel.desafio.api.domain.exception.UsuarioInativoException;
import com.jarddel.desafio.api.domain.model.conta.Conta;
import com.jarddel.desafio.api.domain.model.conta.InformacaoPessoal;
import com.jarddel.desafio.api.domain.model.conta.Tipo;
import com.jarddel.desafio.api.domain.model.usuario.Usuario;
import com.jarddel.desafio.api.infrastructure.service.ApplicationUserDetailsService;

@RunWith(SpringRunner.class)
@RestClientTest({ ContaService.class, UsuarioService.class })
@DataJpaTest
public class ContaServiceTest {

    @Autowired
    private ContaService contaService;

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    public ApplicationUserDetailsService appUserService;

    @Test(expected = UsuarioInativoException.class)
    public void cadastrarContaComUsuarioInativo() {
        Usuario beltrano = usuarioService.buscar(4L);

        assertEquals(false, beltrano.isAtivo());

        InformacaoPessoal info = new InformacaoPessoal("Beltrano", "44444444444", LocalDate.now());
        contaService.salvar(new Conta(info, new BigDecimal("10000.00"), Tipo.PREMIUM, beltrano, LocalDate.now(), true));
    }

    @Test
    public void cadastrarContaComSaldoPreenchidoRetornarSaldoZerado() {
        Usuario beltranoInativo = usuarioService.buscar(4L);
        beltranoInativo.setAtivo(true);
        Usuario beltranoAtivo = usuarioService.salvar(beltranoInativo);

        InformacaoPessoal info = new InformacaoPessoal("Beltrano", "44444444444", LocalDate.now());
        Conta conta = new Conta(info, new BigDecimal("10000.00"), Tipo.PREMIUM, beltranoAtivo, LocalDate.now(), true);
        Conta contaSalva = contaService.salvar(conta);

        assertEquals(new BigDecimal("0"), contaSalva.getSaldo());
    }
}
