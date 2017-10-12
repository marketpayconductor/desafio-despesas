package br.pro.fagnerlima.desafiodespesas.domain.model.exception;

public class ContaInativaException extends RuntimeException {

    private static final long serialVersionUID = -2167960679787391002L;

    public ContaInativaException() {
        super("Conta inativa");
    }
}
