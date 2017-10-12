package br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CadastroLancamentoTO implements Serializable {

    private static final long serialVersionUID = 866990244772381243L;

    @Size(max = 50)
    protected String descricao;

    @NotNull
    protected BigDecimal valor;

    public CadastroLancamentoTO() {

    }

    public CadastroLancamentoTO(String descricao, BigDecimal valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
        CadastroLancamentoTO other = (CadastroLancamentoTO) obj;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (valor == null) {
            if (other.valor != null)
                return false;
        } else if (!valor.equals(other.valor))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("CadastroLancamentoTO [descricao=%s, valor=%s]", descricao, valor);
    }
}
