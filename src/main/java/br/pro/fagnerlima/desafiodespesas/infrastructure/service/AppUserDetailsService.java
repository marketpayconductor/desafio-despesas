package br.pro.fagnerlima.desafiodespesas.infrastructure.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;

public interface AppUserDetailsService extends UserDetailsService {

    public Usuario getUsuario();
}
