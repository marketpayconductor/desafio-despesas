package br.pro.fagnerlima.desafiodespesas.presentation.transferobjectassembler;

import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.CadastroUsuarioTO;

public final class CadastroUsuarioAssembler {

    public Usuario getEntity(CadastroUsuarioTO cadastroUsuarioTO) {
        return new Usuario(cadastroUsuarioTO.getEmail(), cadastroUsuarioTO.getSenha(),
                cadastroUsuarioTO.getInformacaoPessoal());
    }
}
