package com.jarddel.desafio.api.domain.model.usuario;

import java.util.List;

import javax.persistence.Column;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarddel.desafio.api.domain.model.conta.Conta;
import com.jarddel.desafio.api.infrastructure.persistence.hibernate.listener.UsuarioListener;

@Entity
@EntityListeners(UsuarioListener.class)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 32)
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Size(min = 3, max = 32)
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @NotNull
    @Size(min = 6, max = 64)
    @Column(name = "senha", nullable = false)
    private String senha;

    @NotNull
    @Column(name = "ativo")
    private Boolean ativo;

    @JsonIgnore
    @OneToOne(mappedBy = "usuario")
    private Conta conta;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_permissao", 
        joinColumns = @JoinColumn(name = "usuario"), inverseJoinColumns = @JoinColumn(name = "permissao")
    )
    private List<Permissao> permissoes;

    public Usuario() {
    }

    public Usuario(String email, String login, String senha, Boolean ativo, Conta conta, List<Permissao> permissoes) {
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
        this.conta = conta;
        this.permissoes = permissoes;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
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
        return "Usuario [id=" + id + ", login=" + login + "]";
    }

}
