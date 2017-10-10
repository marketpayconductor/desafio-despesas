package com.jarddel.desafio.api.application.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.jarddel.desafio.api.application.service.exception.UsuarioJaCadastradoException;
import com.jarddel.desafio.api.domain.model.usuario.Usuario;
import com.jarddel.desafio.api.infrastructure.service.ApplicationUserDetailsService;

@RunWith(SpringRunner.class)
@RestClientTest(UsuarioService.class)
@DataJpaTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    public ApplicationUserDetailsService appUserService;

    @Test(expected = UsuarioJaCadastradoException.class)
    public void cadastrarUsuarioLoginExistente() {
        usuarioService.cadastrar(new Usuario("admin@email.com", "admin", "_senha_", true, null, null));
    }
}
