package com.jarddel.desafio.api.infrastructure.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jarddel.desafio.api.domain.model.usuario.Usuario;
import com.jarddel.desafio.api.infrastructure.persistence.hibernate.repository.UsuarioRepository;
import com.jarddel.desafio.api.infrastructure.security.UsuarioAuth;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = obterUsuario(login);
        return new UsuarioAuth(usuario, getPermissoes(usuario));
    }

    public Usuario obterUsuarioLogado() {
        String login = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return obterUsuario(login);
    }

    private Usuario obterUsuario(String login) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findOneByLogin(login);
        Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException(
                messageSource.getMessage("recurso.operacao-invalida", null, LocaleContextHolder.getLocale())));
        return usuario;
    }

    private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        usuario.getPermissoes()
                .forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getPapel().getDescricao().toUpperCase())));
        return authorities;
    }

}