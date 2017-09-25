package br.com.conductor.selecao.service;


import br.com.conductor.selecao.model.Usuario;

import java.util.List;

public interface UsuarioService {
	
	Usuario pesquisarUsuarioPorId(Long id);

	Usuario pesquisarUsuarioPorLogin(String login);

	void salvarUsuario(Usuario user);

	void alterarUsuario(Usuario user);

	List<Usuario> listarUsuarios();

	boolean verificarUsuarioExiste(Usuario user);
}