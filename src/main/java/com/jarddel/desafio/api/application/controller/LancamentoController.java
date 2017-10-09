package com.jarddel.desafio.api.application.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jarddel.desafio.api.application.service.LancamentoService;
import com.jarddel.desafio.api.domain.model.lancamento.Despesa;
import com.jarddel.desafio.api.domain.model.lancamento.Lancamento;
import com.jarddel.desafio.api.domain.model.lancamento.Receita;
import com.jarddel.desafio.api.domain.model.lancamento.Transferencia;
import com.jarddel.desafio.api.presentation.dto.LancamentoDTO;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping
    @PreAuthorize("#oauth2.hasScope('read')")
    public Page<Lancamento> meuHistorico(Pageable pageable) {
        return lancamentoService.meuHistorico(pageable);
    }

    @GetMapping("/{idConta}/conta")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') and #oauth2.hasScope('read')")
    public Page<Lancamento> historico(@PathVariable Long idConta, Pageable pageable) {
        return lancamentoService.historico(idConta, pageable);
    }

    @PostMapping("/creditar")
    @PreAuthorize("#oauth2.hasScope('write')")
    public ResponseEntity<Receita> creditar(@Valid @RequestBody LancamentoDTO lancamento) {
        Receita receita = lancamentoService.creditar(lancamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(receita);
    }

    @PostMapping("/debitar")
    @PreAuthorize("#oauth2.hasScope('write')")
    public ResponseEntity<Despesa> debitar(@Valid @RequestBody LancamentoDTO lancamento) {
        Despesa despesa = lancamentoService.debitar(lancamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesa);
    }

    @PostMapping("/transferir")
    @PreAuthorize("#oauth2.hasScope('write')")
    public ResponseEntity<Transferencia> transferir(@Valid @RequestBody LancamentoDTO lancamento) {
        Transferencia transferencia = lancamentoService.transferir(lancamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(transferencia);
    }
}
