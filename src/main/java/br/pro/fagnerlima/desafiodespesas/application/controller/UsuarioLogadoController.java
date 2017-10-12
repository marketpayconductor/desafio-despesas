package br.pro.fagnerlima.desafiodespesas.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;
import br.pro.fagnerlima.desafiodespesas.infrastructure.service.AppUserDetailsService;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.ResponseTO;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.UsuarioTO;
import br.pro.fagnerlima.desafiodespesas.presentation.transferobjectassembler.UsuarioAssembler;

@RestController
@RequestMapping("/me")
public class UsuarioLogadoController {

    @Autowired
    private AppUserDetailsService userDetailsService;

    @GetMapping
    public ResponseEntity<ResponseTO<UsuarioTO>> me() {
        Usuario usuario = userDetailsService.getUsuario();
        UsuarioTO usuarioTO = (new UsuarioAssembler()).getData(usuario);
        ResponseTO<UsuarioTO> responseTO = new ResponseTO<>(usuarioTO);

        return ResponseEntity.ok(responseTO);
    }
}
