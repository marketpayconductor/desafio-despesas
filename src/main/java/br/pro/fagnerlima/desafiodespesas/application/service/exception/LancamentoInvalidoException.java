package br.pro.fagnerlima.desafiodespesas.application.service.exception;

public class LancamentoInvalidoException extends RuntimeException {

    private static final long serialVersionUID = 8550619558930297743L;

    public LancamentoInvalidoException() {
        super("Lançamento inválido");
    }
}
