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
    public List<Lancamento> meuHistorico() {
        return lancamentoService.meuHistorico();
    }

    @GetMapping("/{idConta}/conta")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Lancamento> historico(@PathVariable Long idConta) {
        return lancamentoService.historico(idConta);
    }

    @PostMapping("/creditar")
    public ResponseEntity<Receita> creditar(@Valid @RequestBody LancamentoDTO lancamento) {
        Receita receita = lancamentoService.creditar(lancamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(receita);
    }

    @PostMapping("/debitar")
    public ResponseEntity<Despesa> debitar(@Valid @RequestBody LancamentoDTO lancamento) {
        Despesa despesa = lancamentoService.debitar(lancamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesa);
    }

    @PostMapping("/transferir")
    public ResponseEntity<Transferencia> transferir(@Valid @RequestBody LancamentoDTO lancamento) {
        Transferencia transferencia = lancamentoService.transferir(lancamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(transferencia);
    }
}
