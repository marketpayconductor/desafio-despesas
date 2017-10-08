package com.jarddel.desafio.api.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jarddel.desafio.api.application.service.exception.UsuarioJaCadastradoException;
import com.jarddel.desafio.api.domain.model.usuario.Usuario;
import com.jarddel.desafio.api.domain.service.UsuarioServiceInterface;
import com.jarddel.desafio.api.infrastructure.persistence.hibernate.repository.UsuarioRepository;

@Service
public class UsuarioService extends BaseService<Usuario> implements UsuarioServiceInterface {

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Override
    protected UsuarioRepository getRepository() {
        return usuarioRepository;
    }

    public Usuario cadastrar(Usuario usuario) {
        verificarLogin(usuario.getLogin());
        return salvar(usuario);
    }

    public Usuario atualizarMe(Usuario usuario) {
        Usuario usuarioLogado = userDetailsService.obterUsuarioLogado();

        if (!usuarioLogado.getLogin().equals(usuario.getLogin())) {
            verificarLogin(usuario.getLogin());
        }

        return atualizar(usuarioLogado.getId(), usuario);
    }

    public Usuario atualizarPropriedadeAtivo(Boolean ativo) {
        Usuario usuario = userDetailsService.obterUsuarioLogado();
        usuario.setAtivo(ativo);
        return salvar(usuario);
    }

    private void verificarLogin(String login) {
        Optional<Usuario> usuario = usuarioRepository.findOneByLogin(login);

        if (usuario.isPresent()) {
            throw new UsuarioJaCadastradoException();
        }
    }
}
