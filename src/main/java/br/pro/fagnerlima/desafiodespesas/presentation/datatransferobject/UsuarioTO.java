package br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject;

import java.io.Serializable;

import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.valueobject.InformacaoPessoal;

public final class UsuarioTO implements Serializable {

    private static final long serialVersionUID = -6457769604732685468L;

    private Long id;
    private String email;
    private boolean ativo;
    private InformacaoPessoal informacaoPessoal;

    public UsuarioTO() {

    }

    public UsuarioTO(Long id, String email, boolean ativo, InformacaoPessoal informacaoPessoal) {
        this.id = id;
        this.email = email;
        this.ativo = ativo;
        this.informacaoPessoal = informacaoPessoal;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public InformacaoPessoal getInformacaoPessoal() {
        return informacaoPessoal;
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
        UsuarioTO other = (UsuarioTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("UsuarioTO [id=%s, email=%s, ativo=%s, informacaoPessoal=%s]", id, email, ativo,
                informacaoPessoal);
    }
}
