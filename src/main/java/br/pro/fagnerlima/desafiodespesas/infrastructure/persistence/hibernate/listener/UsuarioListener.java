package br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.listener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;

public class UsuarioListener {

    @PrePersist
    public void usuarioPrePersist(Usuario usuario) {
        usuario.setAtivo(true);
        usuario.setDataCriacao(LocalDateTime.now());
        usuario.setDataAtualizacao(LocalDateTime.now());
    }

    @PreUpdate
    public void usuarioPreUpdate(Usuario usuario) {
        usuario.setDataAtualizacao(LocalDateTime.now());
    }
}
