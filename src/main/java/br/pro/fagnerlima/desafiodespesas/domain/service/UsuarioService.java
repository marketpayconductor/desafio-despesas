package br.pro.fagnerlima.desafiodespesas.domain.service;

import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;

public interface UsuarioService extends BaseService<Usuario, Long> {

    /**
     * Atualiza a propriedade ativo.
     * 
     * @param id
     */
    public void updateAtivo(Long id);
}
