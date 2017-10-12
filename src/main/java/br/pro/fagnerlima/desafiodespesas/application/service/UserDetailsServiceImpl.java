package br.pro.fagnerlima.desafiodespesas.application.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository.UsuarioRepository;
import br.pro.fagnerlima.desafiodespesas.infrastructure.security.UserAuth;
import br.pro.fagnerlima.desafiodespesas.infrastructure.service.AppUserDetailsService;

@Service
public class UserDetailsServiceImpl implements AppUserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(username);
        Usuario usuario = usuarioOptional
                .orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha inválido(s)"));

        return new UserAuth(usuario, getPermissoes(usuario));
    }

    public Usuario getUsuario() {
        UserAuth userAuth = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userAuth.getUsuario();
    }

    private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        usuario.getPermissoes().forEach(
                permissao -> authorities.add(new SimpleGrantedAuthority(permissao.getDescricao().toUpperCase())));

        return authorities;
    }
}
