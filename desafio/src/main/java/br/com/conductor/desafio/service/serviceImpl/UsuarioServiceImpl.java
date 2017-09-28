package br.com.conductor.desafio.service.serviceImpl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.conductor.desafio.dto.ResponseDTO;
import br.com.conductor.desafio.dto.UsuarioResponseDTO;
import br.com.conductor.desafio.exception.ApplicationException;
import br.com.conductor.desafio.exception.CpfNaoInformadoException;
import br.com.conductor.desafio.exception.UsariosNaoCadastradosException;
import br.com.conductor.desafio.exception.UsuarioJaCadastradoException;
import br.com.conductor.desafio.exception.UsuarioNaoEncontradoException;
import br.com.conductor.desafio.model.Usuario;
import br.com.conductor.desafio.repository.UsuarioRepository;
import br.com.conductor.desafio.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private Logger logger = Logger.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuarioRepository repository;

	@Override
	public ResponseDTO inserirUsuario(Usuario usuario) throws ApplicationException {

		ResponseDTO response = new ResponseDTO();

		verificaCpf(usuario.getCpf());

		try {

			Usuario user = repository.findByCpf(usuario.getCpf());

			if (user != null) {
				throw new UsuarioJaCadastradoException();
			}

			repository.save(usuario);

			response = new ResponseDTO(HttpStatus.OK, "Usuário cadastrado com sucesso");

		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		return response;
	}

	@Override
	public ResponseDTO desativarUsuario(String cpf) throws ApplicationException {

		ResponseDTO response = new ResponseDTO();

		verificaCpf(cpf);

		try {

			Usuario usuario = repository.findByCpf(cpf);

			if (usuario == null) {
				throw new UsuarioNaoEncontradoException();
			}

			usuario.setAtivo(false);
			repository.save(usuario);

			response = new ResponseDTO(HttpStatus.OK, "Usuário desativado com sucesso");

		} catch (Exception e) {
			logger.error(e);
			throw e;
		}

		return response;
	}

	@Override
	public UsuarioResponseDTO listarUsuarios() throws ApplicationException {

		UsuarioResponseDTO response = new UsuarioResponseDTO();

		try {
			List<Usuario> usuarios = repository.findAll();

			if ((usuarios.size() == 0) || (usuarios == null)) {
				throw new UsariosNaoCadastradosException();
			}
			response.setUsuarios(usuarios);

			response.setResponse(new ResponseDTO(HttpStatus.OK, "Operação realizada com sucesso"));

		} catch (Exception e) {
			logger.error(e);
			throw e;
		}

		return response;
	}

	private void verificaCpf(String cpf) throws ApplicationException {
		if (StringUtils.isEmpty(cpf)) {
			throw new CpfNaoInformadoException();
		}
	}
}
