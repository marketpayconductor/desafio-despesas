package br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.listener;

import java.time.LocalDate;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;

public class UsuarioListener {

    @PrePersist
    public void usuarioPrePersist(Usuario usuario) {
        usuario.setAtivo(true);
        usuario.setDataCriacao(LocalDate.now());
        usuario.setDataAtualizacao(LocalDate.now());
    }

    @PreUpdate
    public void usuarioPreUpdate(Usuario usuario) {
        usuario.setDataAtualizacao(LocalDate.now());
    }
}
