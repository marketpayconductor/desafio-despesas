package br.pro.fagnerlima.desafiodespesas.application.service.exception;

import org.springframework.dao.EmptyResultDataAccessException;

public class UsuarioNaoEncontradoException extends EmptyResultDataAccessException {

    private static final long serialVersionUID = 263445685861278885L;

    public UsuarioNaoEncontradoException() {
        super("Usuário não encontrado", 1);
    }
}
