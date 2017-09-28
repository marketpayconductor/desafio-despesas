package br.com.conductor.desafio.service;

import br.com.conductor.desafio.dto.ResponseDTO;
import br.com.conductor.desafio.dto.UsuarioResponseDTO;
import br.com.conductor.desafio.exception.ApplicationException;
import br.com.conductor.desafio.model.Usuario;

public interface UsuarioService {

	ResponseDTO inserirUsuario(Usuario usuario) throws ApplicationException;

	ResponseDTO desativarUsuario(String cpf) throws ApplicationException;

	UsuarioResponseDTO listarUsuarios() throws ApplicationException;
}