package br.pro.fagnerlima.desafiodespesas.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.pro.fagnerlima.desafiodespesas.application.service.exception.UsuarioNaoEncontradoException;
import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;
import br.pro.fagnerlima.desafiodespesas.domain.service.UsuarioService;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository.BaseRepository;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario findOne(Long id) throws UsuarioNaoEncontradoException {
        try {
            return super.findOne(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new UsuarioNaoEncontradoException();
        }
    }

    @Override
    public void updateAtivo(Long id) {
        Usuario usuario = findOne(id);
        usuario.changeAtivo();

        usuarioRepository.save(usuario);
    }

    @Override
    protected BaseRepository<Usuario, Long> getRepository() {
        return usuarioRepository;
    }
}
