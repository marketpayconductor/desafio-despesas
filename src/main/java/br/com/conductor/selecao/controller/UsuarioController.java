package br.com.conductor.selecao.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.conductor.selecao.model.Usuario;
import br.com.conductor.selecao.service.UsuarioService;
import br.com.conductor.selecao.util.ApiErrorMessage;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	public static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	UsuarioService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		logger.info("Chamando o metodo: UsuarioController.listarUsuarios()");
		List<Usuario> usuarios = userService.listarUsuarios();
		if (usuarios.isEmpty()) {
			logger.error("N\u00E3o existem usu\u00E1rios cadastrados");
			return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> pesquisarUsuario(@PathVariable("id") long id) {
		logger.info("Chamando o metodo: UsuarioController.pesquisarUsuario()");
		Usuario usuario = userService.pesquisarUsuarioPorId(id);
		if (usuario == null) {
			logger.error("Usu\u00E1rio n\u00E3o encontrado");
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage("Usu\u00E1rio n\u00E3o encontrado"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> salvarUsuario(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder) {
		logger.info("Chamando o metodo: UsuarioController.salvarUsuario()");
		if (userService.verificarUsuarioExiste(usuario)) {
			logger.error("Usu\u00E1rio j\u00E1 cadastrado");
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage("Usu\u00E1rio j\u00E1 cadastrado"),
					HttpStatus.CONFLICT);
		}
		userService.salvarUsuario(usuario);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/usuario/{id}").buildAndExpand(usuario.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> alterarUsuario(@PathVariable("id") long id, @RequestBody Usuario usuarioParam) {
		logger.info("Chamando o metodo: UsuarioController.alterarUsuario()");
		Usuario usuario = userService.pesquisarUsuarioPorId(id);

		if (usuario == null) {
			logger.error("Usu\u00E1rio n\u00E3o encontrado");
			return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage("Usu\u00E1rio n\u00E3o encontrado"),
					HttpStatus.NOT_FOUND);
		}

		usuario.setLogin(usuarioParam.getLogin());
		usuario.setSenha(usuarioParam.getSenha());

		userService.alterarUsuario(usuario);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

}