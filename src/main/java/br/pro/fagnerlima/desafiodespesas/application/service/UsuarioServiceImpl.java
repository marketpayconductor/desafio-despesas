package br.pro.fagnerlima.desafiodespesas.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.pro.fagnerlima.desafiodespesas.application.service.exception.UsuarioNaoEncontradoException;
import br.pro.fagnerlima.desafiodespesas.domain.model.conta.entity.Conta;
import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;
import br.pro.fagnerlima.desafiodespesas.domain.service.UsuarioService;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository.BaseRepository;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository.ContaRepository;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public Usuario findOne(Long id) throws UsuarioNaoEncontradoException {
        try {
            return super.findOne(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new UsuarioNaoEncontradoException();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Usuario save(Usuario usuario) {
        usuario = super.save(usuario);

        Conta conta = new Conta(usuario);
        contaRepository.save(conta);

        return usuario;
    }

    @Override
    protected BaseRepository<Usuario, Long> getRepository() {
        return usuarioRepository;
    }
}
