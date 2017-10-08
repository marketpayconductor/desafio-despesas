package com.jarddel.desafio.api.application.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jarddel.desafio.api.application.service.UsuarioService;
import com.jarddel.desafio.api.domain.model.usuario.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Usuario buscar(@PathVariable Long id) {
        return usuarioService.buscar(id);
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioService.cadastrar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @PutMapping("/")
    public ResponseEntity<Usuario> atualizarMe(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioService.atualizarMe(usuario);
        return ResponseEntity.ok(usuarioSalvo);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioService.atualizar(id, usuario);
        return ResponseEntity.ok(usuarioSalvo);
    }

    @PutMapping("/inativar")
    public ResponseEntity<Usuario> inativarMe() {
        Usuario usuarioSalvo = usuarioService.atualizarPropriedadeAtivo(false);
        return ResponseEntity.ok(usuarioSalvo);
    }

    @PutMapping("/ativar")
    public ResponseEntity<Usuario> ativarMe() {
        Usuario usuarioSalvo = usuarioService.atualizarPropriedadeAtivo(true);
        return ResponseEntity.ok(usuarioSalvo);
    }

}
