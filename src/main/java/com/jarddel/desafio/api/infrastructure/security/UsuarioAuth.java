package com.jarddel.desafio.api.infrastructure.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.jarddel.desafio.api.domain.model.usuario.Usuario;

public class UsuarioAuth extends User {

    private static final long serialVersionUID = 5207543531631158139L;

    private Usuario usuario;

    public UsuarioAuth(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getLogin(), usuario.getSenha(), authorities);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

}