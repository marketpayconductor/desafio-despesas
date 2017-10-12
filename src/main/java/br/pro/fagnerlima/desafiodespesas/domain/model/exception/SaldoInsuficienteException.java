package br.pro.fagnerlima.desafiodespesas.domain.model.exception;

public class SaldoInsuficienteException extends RuntimeException {

    private static final long serialVersionUID = -5321884664134849991L;

    public SaldoInsuficienteException() {
        super("Saldo insuficiente");
    }
}
