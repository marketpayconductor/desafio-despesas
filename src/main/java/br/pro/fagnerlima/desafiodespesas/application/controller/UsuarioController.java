package br.pro.fagnerlima.desafiodespesas.application.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.pro.fagnerlima.desafiodespesas.application.service.UserDetailsServiceImpl;
import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;
import br.pro.fagnerlima.desafiodespesas.domain.service.UsuarioService;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.CadastroUsuarioTO;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.ResponseTO;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.UsuarioTO;
import br.pro.fagnerlima.desafiodespesas.presentation.transferobjectassembler.CadastroUsuarioAssembler;
import br.pro.fagnerlima.desafiodespesas.presentation.transferobjectassembler.UsuarioAssembler;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseTO<Page<UsuarioTO>>> findAll(Pageable pageable) {
        Page<Usuario> usuarios = usuarioService.findAll(pageable);

        if (0 == usuarios.getTotalElements()) {
            return ResponseEntity.noContent().build();
        }

        Page<UsuarioTO> usuariosTO = usuarios.map(usuario -> (new UsuarioAssembler()).getData(usuario));
        ResponseTO<Page<UsuarioTO>> responseDto = new ResponseTO<>(usuariosTO);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseTO<UsuarioTO>> getMe() {
        Usuario usuario = userDetailsService.getUsuario();
        UsuarioTO usuarioTO = (new UsuarioAssembler()).getData(usuario);
        ResponseTO<UsuarioTO> responseTO = new ResponseTO<>(usuarioTO);

        return ResponseEntity.ok(responseTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseTO<UsuarioTO>> findOne(@PathVariable Long id) {
        Usuario usuario = usuarioService.findOne(id);
        UsuarioTO usuarioTO = (new UsuarioAssembler()).getData(usuario);
        ResponseTO<UsuarioTO> responseTO = new ResponseTO<>(usuarioTO);

        return ResponseEntity.ok(responseTO);
    }

    @PostMapping
    public ResponseEntity<ResponseTO<UsuarioTO>> save(@Valid @RequestBody CadastroUsuarioTO cadastroUsuarioTO) {
        Usuario usuario = (new CadastroUsuarioAssembler()).getEntity(cadastroUsuarioTO);
        usuario = usuarioService.save(usuario);

        UsuarioTO usuarioTO = (new UsuarioAssembler()).getData(usuario);
        ResponseTO<UsuarioTO> responseTO = new ResponseTO<>(usuarioTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseTO<UsuarioTO>> update(@PathVariable Long id,
            @Valid @RequestBody CadastroUsuarioTO cadastroUsuarioTO) {
        Usuario usuario = (new CadastroUsuarioAssembler()).getEntity(cadastroUsuarioTO);
        usuario = usuarioService.update(id, usuario);

        UsuarioTO usuarioTO = (new UsuarioAssembler()).getData(usuario);
        ResponseTO<UsuarioTO> responseTO = new ResponseTO<>(usuarioTO);

        return ResponseEntity.ok(responseTO);
    }

    @PutMapping
    public ResponseEntity<ResponseTO<UsuarioTO>> updateMe(@Valid @RequestBody CadastroUsuarioTO cadastroUsuarioTO) {
        Usuario usuario = (new CadastroUsuarioAssembler()).getEntity(cadastroUsuarioTO);
        usuario = usuarioService.update(userDetailsService.getUsuario().getId(), usuario);

        UsuarioTO usuarioTO = (new UsuarioAssembler()).getData(usuario);
        ResponseTO<UsuarioTO> responseTO = new ResponseTO<>(usuarioTO);

        return ResponseEntity.ok(responseTO);
    }

    @PutMapping("/{id}/ativo")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAtivo(@PathVariable Long id) {
        usuarioService.updateAtivo(id);
    } 

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMe() {
        usuarioService.delete(userDetailsService.getUsuario().getId());
    }
}
