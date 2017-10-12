package br.pro.fagnerlima.desafiodespesas.application.service.exception;

import org.springframework.dao.EmptyResultDataAccessException;

public class ContaNaoEncontradaException extends EmptyResultDataAccessException {

    private static final long serialVersionUID = 4579440236581712053L;

    public ContaNaoEncontradaException() {
        super("Conta não encontrada", 1);
    }
}
