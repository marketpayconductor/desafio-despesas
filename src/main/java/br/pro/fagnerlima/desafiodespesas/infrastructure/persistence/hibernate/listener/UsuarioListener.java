package br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.listener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.pro.fagnerlima.desafiodespesas.application.util.PasswordEncoderUtils;
import br.pro.fagnerlima.desafiodespesas.domain.model.conta.entity.Conta;
import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;

public class UsuarioListener {

    @PrePersist
    public void usuarioPrePersist(Usuario usuario) {
        usuario.setConta(new Conta(usuario));
        usuario.setDataCriacao(LocalDateTime.now());
        usuario.setAtivo(true);
        usuario.setSenha(PasswordEncoderUtils.encode(usuario.getSenha()));
        usuario.setDataAtualizacao(LocalDateTime.now());
    }

    @PreUpdate
    public void usuarioPreUpdate(Usuario usuario) {
        usuario.setSenha(PasswordEncoderUtils.encode(usuario.getSenha()));
        usuario.setDataAtualizacao(LocalDateTime.now());
    }
}
