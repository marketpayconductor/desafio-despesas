package br.pro.fagnerlima.desafiodespesas.domain.service;

import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;

public interface UsuarioService extends BaseService<Usuario, Long> {

    /**
     * Altera o status da propriedade ativo.
     * 
     * @param id
     */
    public void updateAtivo(Long id);
}
