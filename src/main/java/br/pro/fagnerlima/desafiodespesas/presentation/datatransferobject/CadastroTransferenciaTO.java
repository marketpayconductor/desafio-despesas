package br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class CadastroTransferenciaTO extends CadastroLancamentoTO {

    private static final long serialVersionUID = 7520028684237130370L;

    @NotNull
    private Long contaDestino;

    public CadastroTransferenciaTO() {

    }

    public CadastroTransferenciaTO(String descricao, BigDecimal valor, Long contaDestino) {
        super(descricao, valor);
        this.contaDestino = contaDestino;
    }

    public Long getContaDestino() {
        return contaDestino;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((contaDestino == null) ? 0 : contaDestino.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        CadastroTransferenciaTO other = (CadastroTransferenciaTO) obj;
        if (contaDestino == null) {
            if (other.contaDestino != null)
                return false;
        } else if (!contaDestino.equals(other.contaDestino))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("CadastroTransferenciaTO [contaDestino=%s]", contaDestino);
    }
}
