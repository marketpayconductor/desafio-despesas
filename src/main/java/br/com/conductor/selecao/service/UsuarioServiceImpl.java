package br.com.conductor.selecao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.conductor.selecao.model.Usuario;
import br.com.conductor.selecao.repository.UsuarioRepository;

@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public Usuario pesquisarUsuarioPorId(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public Usuario pesquisarUsuarioPorLogin(String name) {
		return userRepository.findByLogin(name);
	}

	@Override
	public void salvarUsuario(Usuario user) {
		userRepository.save(user);
	}

	@Override
	public void alterarUsuario(Usuario user) {
		salvarUsuario(user);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return userRepository.findAll();
	}

	@Override
	public boolean verificarUsuarioExiste(Usuario user) {
		return pesquisarUsuarioPorLogin(user.getLogin()) != null;
	}

}
