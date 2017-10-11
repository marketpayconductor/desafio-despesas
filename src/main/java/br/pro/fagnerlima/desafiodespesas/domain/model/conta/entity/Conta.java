package br.pro.fagnerlima.desafiodespesas.domain.model.conta.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.listener.ContaListener;

@Entity
@EntityListeners(ContaListener.class)
@Table(name = "conta")
public class Conta implements Serializable {

    private static final long serialVersionUID = -5964028713806821385L;

    @Id
    @GeneratedValue(generator = "fk_conta_usuario")
    @GenericGenerator(name = "fk_conta_usuario", strategy = "foreign",
        parameters = @Parameter(name = "property", value = "usuario"))
    @Column(name = "id_usuario")
    private Long id;

    @OneToOne(mappedBy = "conta")
    @PrimaryKeyJoinColumn
    private Usuario usuario;

    @NotNull
    private BigDecimal saldo;

    @NotNull
    private boolean ativo;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    public Conta() {

    }

    public Conta(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
        if (usuario == null) {
            if (other.usuario != null)
                return false;
        } else if (!usuario.equals(other.usuario))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Conta [usuario=%s, saldo=%s, ativo=%s]", usuario, saldo, ativo);
    }
}
