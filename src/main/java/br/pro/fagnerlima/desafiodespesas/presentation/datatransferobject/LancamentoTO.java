package br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.enumeration.TipoLancamento;

public final class LancamentoTO implements Serializable {

    private static final long serialVersionUID = 8939541595508111598L;

    private Long id;
    private LocalDateTime data;
    private TipoLancamento tipo;
    private String descricao;
    private BigDecimal valor;

    public LancamentoTO() {

    }

    public LancamentoTO(Long id, LocalDateTime data, TipoLancamento tipo, String descricao, BigDecimal valor) {
        this.id = id;
        this.data = data;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
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
        LancamentoTO other = (LancamentoTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("LancamentoTO [id=%s, data=%s, tipo=%s, descricao=%s, valor=%s]", id, data, tipo,
                descricao, valor);
    }
}
