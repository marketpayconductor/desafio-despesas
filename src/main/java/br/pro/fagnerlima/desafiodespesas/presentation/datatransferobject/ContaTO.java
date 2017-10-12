package br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject;

import java.io.Serializable;
import java.math.BigDecimal;

public final class ContaTO implements Serializable {

    private static final long serialVersionUID = 8002931088946622390L;

    private Long id;
    private String usuario;
    private BigDecimal saldo;
    private boolean ativo;

    public ContaTO() {

    }

    public ContaTO(Long id, String usuario, BigDecimal saldo, boolean ativo) {
        super();
        this.id = id;
        this.usuario = usuario;
        this.saldo = saldo;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContaTO other = (ContaTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("ContaTO [id=%s, usuario=%s, saldo=%s, ativo=%s]", id, usuario, saldo, ativo);
    }
}
