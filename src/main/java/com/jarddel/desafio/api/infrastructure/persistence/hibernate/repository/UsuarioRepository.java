package com.jarddel.desafio.api.infrastructure.persistence.hibernate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jarddel.desafio.api.domain.model.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findOneByLogin(String login);

}
