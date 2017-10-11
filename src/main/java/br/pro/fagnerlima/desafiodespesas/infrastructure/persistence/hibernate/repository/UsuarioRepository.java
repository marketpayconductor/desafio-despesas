package br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository;

import java.util.Optional;

import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;

public interface UsuarioRepository extends BaseRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);
}
