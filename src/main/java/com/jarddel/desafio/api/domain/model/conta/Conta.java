package com.jarddel.desafio.api.domain.model.conta;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.jarddel.desafio.api.domain.exception.SaldoInsuficienteException;
import com.jarddel.desafio.api.domain.model.usuario.Usuario;
import com.jarddel.desafio.api.infrastructure.persistence.hibernate.listener.ContaListener;

@Entity
@Table(name = "conta")
@EntityListeners(ContaListener.class)
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private InformacaoPessoal informacaoPessoal;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @NotNull
    @Column(name = "ativo")
    private Boolean ativo;

    @Version
    private Integer version;

    public Conta() {
    }

    public Conta(InformacaoPessoal informacaoPessoal, BigDecimal saldo, Tipo tipo, Usuario usuario,
            LocalDate dataCriacao, Boolean ativo) {
        this.informacaoPessoal = informacaoPessoal;
        this.saldo = saldo;
        this.tipo = tipo;
        this.usuario = usuario;
        this.dataCriacao = dataCriacao;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public InformacaoPessoal getInformacaoPessoal() {
        return informacaoPessoal;
    }

    public void setInformacaoPessoal(InformacaoPessoal informacaoPessoal) {
        this.informacaoPessoal = informacaoPessoal;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void zerarSaldo() {
        this.saldo = new BigDecimal(BigInteger.ZERO);
    }

    public void creditar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    public void debitar(BigDecimal valor) {
        if (0 > this.saldo.compareTo(valor)) {
            throw new SaldoInsuficienteException();
        }

        this.saldo = this.saldo.subtract(valor);
    }

    public void defineDataDefault() {
        this.dataCriacao = LocalDate.now();
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
        Conta other = (Conta) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Conta [id=" + id + ", informacaoPessoal=" + informacaoPessoal + ", saldo=" + saldo + "]";
    }

}
