package br.pro.fagnerlima.desafiodespesas.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;
import br.pro.fagnerlima.desafiodespesas.domain.service.UsuarioService;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository.BaseRepository;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected BaseRepository<Usuario, Long> getRepository() {
        return usuarioRepository;
    }
}
