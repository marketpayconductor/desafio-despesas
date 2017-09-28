package br.com.conductor.desafio.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.conductor.desafio.dto.ResponseDTO;
import br.com.conductor.desafio.dto.UsuarioResponseDTO;
import br.com.conductor.desafio.exception.ApplicationException;
import br.com.conductor.desafio.model.Usuario;
import br.com.conductor.desafio.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	private Logger logger = Logger.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> cadastrarUsuario(@RequestBody Usuario usuario) {

		ResponseDTO response = new ResponseDTO();

		try {

			response = usuarioService.inserirUsuario(usuario);

		} catch (ApplicationException e) {
			e.printStackTrace();
			logger.error(e);
			return new ResponseEntity<ResponseDTO>(response = new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage()),
					HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return new ResponseEntity<ResponseDTO>(
					response = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<UsuarioResponseDTO> getUsuarios() {

		UsuarioResponseDTO response = new UsuarioResponseDTO();

		try {

			response = usuarioService.listarUsuarios();

		} catch (ApplicationException e) {
			e.printStackTrace();
			logger.error(e);
			response.setResponse(new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage()));
			return new ResponseEntity<UsuarioResponseDTO>(response, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			response.setResponse(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
			return new ResponseEntity<UsuarioResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<UsuarioResponseDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "{cpf}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO> desativarUsuario(@PathVariable("cpf") String cpf) {

		ResponseDTO response = new ResponseDTO();

		try {

			response = usuarioService.desativarUsuario(cpf);

		} catch (ApplicationException e) {
			e.printStackTrace();
			logger.error(e);
			return new ResponseEntity<ResponseDTO>(response = new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage()),
					HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return new ResponseEntity<ResponseDTO>(
					response = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

}
