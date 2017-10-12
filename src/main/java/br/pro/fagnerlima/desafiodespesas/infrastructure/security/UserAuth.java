package br.pro.fagnerlima.desafiodespesas.infrastructure.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;

public class UserAuth extends User {

    private static final long serialVersionUID = -8608616617415134583L;

    private Usuario usuario;

    public UserAuth(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getEmail(), usuario.getSenha(), authorities);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
