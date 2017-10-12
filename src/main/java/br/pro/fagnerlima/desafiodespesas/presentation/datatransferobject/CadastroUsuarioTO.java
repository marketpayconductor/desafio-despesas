package br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.valueobject.InformacaoPessoal;

public final class CadastroUsuarioTO implements Serializable {

    private static final long serialVersionUID = -3679805109488279249L;

    @NotBlank
    @Email
    private String email;

    @Size(min = 8, max = 16)
    private String senha;

    @NotNull
    @Valid
    private InformacaoPessoal informacaoPessoal;

    private boolean ativo = true;

    public CadastroUsuarioTO() {

    }

    public CadastroUsuarioTO(String email, String senha, InformacaoPessoal informacaoPessoal) {
        this.email = email;
        this.senha = senha;
        this.informacaoPessoal = informacaoPessoal;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public InformacaoPessoal getInformacaoPessoal() {
        return informacaoPessoal;
    }

    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (ativo ? 1231 : 1237);
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((informacaoPessoal == null) ? 0 : informacaoPessoal.hashCode());
        result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
        CadastroUsuarioTO other = (CadastroUsuarioTO) obj;
        if (ativo != other.ativo)
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (informacaoPessoal == null) {
            if (other.informacaoPessoal != null)
                return false;
        } else if (!informacaoPessoal.equals(other.informacaoPessoal))
            return false;
        if (senha == null) {
            if (other.senha != null)
                return false;
        } else if (!senha.equals(other.senha))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("CadastroUsuarioTO [email=%s, informacaoPessoal=%s, ativo=%s]", email, informacaoPessoal,
                ativo);
    }
}
