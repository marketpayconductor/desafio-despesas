package br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import br.pro.fagnerlima.desafiodespesas.domain.model.conta.entity.Conta;
import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.valueobject.InformacaoPessoal;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.listener.UsuarioListener;

@Entity
@EntityListeners(UsuarioListener.class)
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1624473756642837504L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Email
    @NotEmpty
    @Size(max = 100)
    private String email;

    @NotEmpty
    private String senha;

    @Embedded
    private InformacaoPessoal informacaoPessoal;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @PrimaryKeyJoinColumn
    private Conta conta;

    @NotNull
    private boolean ativo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_permissao", joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_permissao"))
    private List<Permissao> permissoes;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    public Usuario() {

    }

    public Usuario(String email, String senha, InformacaoPessoal informacaoPessoal, boolean ativo) {
        this.email = email;
        this.senha = senha;
        this.informacaoPessoal = informacaoPessoal;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public InformacaoPessoal getInformacaoPessoal() {
        return informacaoPessoal;
    }

    public void setInformacaoPessoal(InformacaoPessoal informacaoPessoal) {
        this.informacaoPessoal = informacaoPessoal;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void changeAtivo() {
        this.ativo = !this.ativo;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public List<Permissao> addPermissao(Permissao permissao) {
        permissoes.add(permissao);

        return permissoes;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
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
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Usuario [id=%s, email=%s, ativo=%s]", id, email, ativo);
    }
}
