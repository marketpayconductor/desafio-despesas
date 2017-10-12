package br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.enumeration.TipoLancamento;

public final class CadastroLancamentoTO implements Serializable {

    private static final long serialVersionUID = 866990244772381243L;

    @NotNull
    private TipoLancamento tipo;

    @Size(max = 50)
    private String descricao;

    @NotNull
    private BigDecimal valor;

    public CadastroLancamentoTO() {

    }

    public CadastroLancamentoTO(TipoLancamento tipo, String descricao, BigDecimal valor) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public TipoLancamento getTipo() {
        return tipo;
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
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
        if (tipo != other.tipo)
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
        return String.format("CadastroLancamentoTO [tipo=%s, descricao=%s, valor=%s]", tipo, descricao, valor);
    }
}
