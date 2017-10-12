package br.pro.fagnerlima.desafiodespesas.presentation.transferobjectassembler;

import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.UsuarioTO;

public final class UsuarioAssembler {

    public UsuarioTO getData(Usuario usuario) {
        return new UsuarioTO(usuario.getId(), usuario.getEmail(), usuario.isAtivo(), usuario.getInformacaoPessoal());
    }
}
