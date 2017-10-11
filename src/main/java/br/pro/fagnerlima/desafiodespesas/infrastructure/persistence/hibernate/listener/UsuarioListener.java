package br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.listener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.pro.fagnerlima.desafiodespesas.application.PasswordEncoderUtils;
import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;

public class UsuarioListener {

    @PrePersist
    public void usuarioPrePersist(Usuario usuario) {
        usuario.setDataCriacao(LocalDateTime.now());
        setDefaultValues(usuario);
    }

    @PreUpdate
    public void usuarioPreUpdate(Usuario usuario) {
        setDefaultValues(usuario);
    }

    private void setDefaultValues(Usuario usuario) {
        usuario.setAtivo(true);
        usuario.setSenha(PasswordEncoderUtils.encode(usuario.getSenha()));
        usuario.setDataAtualizacao(LocalDateTime.now());
    }
}
