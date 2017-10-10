package com.jarddel.desafio.api.domain.model.lancamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

import com.jarddel.desafio.api.domain.model.conta.Conta;
import com.jarddel.desafio.api.infrastructure.persistence.hibernate.listener.LancamentoListener;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@EntityListeners(LancamentoListener.class)
public abstract class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotNull
    @NumberFormat(pattern = "#,##0.00")
    @DecimalMin(value = "0.01")
    @DecimalMax(value = "9999999.99")
    @Column(name = "valor", nullable = false)
    protected BigDecimal valor;

    @Column(name = "data")
    protected LocalDateTime data;

    @Column(name = "tipo", insertable = false, updatable = false)
    protected String tipo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "conta_destino")
    protected Conta contaDestino;

    public Lancamento() {
    }

    public Lancamento(BigDecimal valor, Conta contaDestino) {
        this.valor = valor;
        this.contaDestino = contaDestino;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getTipo() {
        return tipo;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public void defineDataDefault() {
        this.data = LocalDateTime.now();
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
        Lancamento other = (Lancamento) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Lancamento [id=" + id + ", valor=" + valor + ", contaDestino=" + contaDestino + "]";
    }

}
