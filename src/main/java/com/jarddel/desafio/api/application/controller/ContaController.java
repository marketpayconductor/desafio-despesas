package com.jarddel.desafio.api.application.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.jarddel.desafio.api.application.service.ContaService;
import com.jarddel.desafio.api.domain.model.conta.Conta;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') and #oauth2.hasScope('read')")
    public Page<Conta> listar(Pageable pageable) {
        return contaService.listarTodos(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') and #oauth2.hasScope('read')")
    public Conta buscar(@PathVariable Long id) {
        return contaService.buscar(id);
    }

    @GetMapping("/{id}/saldo")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') and #oauth2.hasScope('read')")
    public BigDecimal saldo(@PathVariable Long id) {
        return contaService.obterSaldo(id);
    }

    @GetMapping("/saldo")
    @PreAuthorize("#oauth2.hasScope('read')")
    public BigDecimal saldo() {
        return contaService.obterMeuSaldo();
    }

    @PostMapping
    @PreAuthorize("#oauth2.hasScope('write')")
    public ResponseEntity<Conta> cadastrarMe(@Valid @RequestBody Conta conta) {
        Conta contaSalva = contaService.cadastrarMe(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(contaSalva);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') and #oauth2.hasScope('write')")
    public ResponseEntity<Conta> atualizar(@PathVariable Long id, @Valid @RequestBody Conta conta) {
        Conta contaSalva = contaService.atualizar(id, conta);
        return ResponseEntity.ok(contaSalva);
    }

    @PutMapping("/")
    @PreAuthorize("#oauth2.hasScope('write')")
    public ResponseEntity<Conta> atualizarMe(@Valid @RequestBody Conta conta) {
        Conta contaSalva = contaService.atualizarMe(conta);
        return ResponseEntity.ok(contaSalva);
    }
}
