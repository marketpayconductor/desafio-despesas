package br.pro.fagnerlima.desafiodespesas.application.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.pro.fagnerlima.desafiodespesas.domain.model.conta.entity.Conta;
import br.pro.fagnerlima.desafiodespesas.domain.service.ContaService;
import br.pro.fagnerlima.desafiodespesas.infrastructure.service.AppUserDetailsService;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.ContaTO;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.ResponseTO;
import br.pro.fagnerlima.desafiodespesas.presentation.transferobjectassembler.ContaAssembler;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private AppUserDetailsService userDetailsService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseTO<Page<ContaTO>>> findAll(Pageable pageable) {
        Page<Conta> contas = contaService.findAll(pageable);

        if (0 == contas.getTotalElements()) {
            return ResponseEntity.noContent().build();
        }

        Page<ContaTO> contasTO = contas.map(conta -> (new ContaAssembler()).getData(conta));
        ResponseTO<Page<ContaTO>> responseTO = new ResponseTO<>(contasTO);

        return ResponseEntity.ok(responseTO);
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseTO<ContaTO>> getMe() {
        Conta conta = userDetailsService.getUsuario().getConta();
        ContaTO contaTO = (new ContaAssembler()).getData(conta);
        ResponseTO<ContaTO> responseTO = new ResponseTO<>(contaTO);

        return ResponseEntity.ok(responseTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseTO<ContaTO>> findOne(@PathVariable Long id) {
        Conta conta = contaService.findOne(id);
        ContaTO contaTO = (new ContaAssembler()).getData(conta);
        ResponseTO<ContaTO> responseTO = new ResponseTO<>(contaTO);

        return ResponseEntity.ok(responseTO);
    }
    
    @GetMapping("/{id}/saldo")
    public ResponseEntity<ResponseTO<BigDecimal>> getSaldo(@PathVariable Long id) {
        Conta conta = contaService.findOne(id);
        ResponseTO<BigDecimal> responseTO = new ResponseTO<>(conta.getSaldo());
        
        return ResponseEntity.ok(responseTO);
    }

    @GetMapping("/saldo")
    public ResponseEntity<ResponseTO<BigDecimal>> getSaldoMe() {
        Conta conta = userDetailsService.getUsuario().getConta();
        ResponseTO<BigDecimal> responseTO = new ResponseTO<>(conta.getSaldo());

        return ResponseEntity.ok(responseTO);
    }

    @PutMapping("/{id}/ativo")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAtivo(@PathVariable Long id) {
        contaService.updateAtivo(id);
    }
}
