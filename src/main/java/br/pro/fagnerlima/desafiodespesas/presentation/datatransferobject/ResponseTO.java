package br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject;

import java.io.Serializable;
import java.util.List;

/**
 * DTO responsável por encapsular os dados de retorno de chamadas à API.
 * 
 * @author Fagner Lima
 *
 * @param <T>
 */
public final class ResponseTO<T> implements Serializable {

    private static final long serialVersionUID = -2880004315058771878L;

    private T data;
    private List<String> errors;

    public ResponseTO() {

    }

    public ResponseTO(T data, List<String> errors) {
        this.data = data;
        this.errors = errors;
    }

    public ResponseTO(T data) {
        this.data = data;
    }

    public ResponseTO(List<String> errors) {
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return String.format("ResponseTO [data=%s, errors=%s]", data, errors);
    }
}
