package br.pro.fagnerlima.desafiodespesas.application.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pro.fagnerlima.desafiodespesas.application.service.UserDetailsServiceImpl;
import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.entity.Lancamento;
import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.enumeration.TipoLancamento;
import br.pro.fagnerlima.desafiodespesas.domain.service.LancamentoService;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.CadastroLancamentoTO;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.CadastroTransferenciaTO;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.LancamentoTO;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.ResponseTO;
import br.pro.fagnerlima.desafiodespesas.presentation.transferobjectassembler.CadastroLancamentoAssembler;
import br.pro.fagnerlima.desafiodespesas.presentation.transferobjectassembler.LancamentoAssembler;

@RestController
@RequestMapping("lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping
    public ResponseEntity<ResponseTO<Page<LancamentoTO>>> getHistorico(Pageable pageable) {
        Page<Lancamento> lancamentos = lancamentoService.findByConta(userDetailsService.getUsuario().getConta(),
                pageable);

        if (0 == lancamentos.getTotalElements()) {
            return ResponseEntity.noContent().build();
        }

        Page<LancamentoTO> lancamentosTO = lancamentos
                .map(lancamento -> (new LancamentoAssembler()).getData(lancamento));
        ResponseTO<Page<LancamentoTO>> responseTO = new ResponseTO<>(lancamentosTO);

        return ResponseEntity.ok(responseTO);
    }

    @PostMapping("/credito")
    public ResponseEntity<ResponseTO<LancamentoTO>> creditar(
            @Valid @RequestBody CadastroLancamentoTO cadastroLancamentoTO) {
        Lancamento lancamento = (new CadastroLancamentoAssembler()).getEntity(cadastroLancamentoTO,
                userDetailsService.getUsuario().getConta(), TipoLancamento.RECEITA);
        lancamento = lancamentoService.creditar(lancamento);

        LancamentoTO lancamentoTO = (new LancamentoAssembler()).getData(lancamento);
        ResponseTO<LancamentoTO> responseTO = new ResponseTO<>(lancamentoTO);

        return ResponseEntity.ok(responseTO);
    }

    @PostMapping("/debito")
    public ResponseEntity<ResponseTO<LancamentoTO>> debitar(
            @Valid @RequestBody CadastroLancamentoTO cadastroLancamentoTO) throws Exception {
        Lancamento lancamento = (new CadastroLancamentoAssembler()).getEntity(cadastroLancamentoTO,
                userDetailsService.getUsuario().getConta(), TipoLancamento.DESPESA);
        lancamento = lancamentoService.debitar(lancamento);

        LancamentoTO lancamentoTO = (new LancamentoAssembler()).getData(lancamento);
        ResponseTO<LancamentoTO> responseTO = new ResponseTO<>(lancamentoTO);

        return ResponseEntity.ok(responseTO);
    }

    @PostMapping("/transferencia")
    public ResponseEntity<ResponseTO<LancamentoTO>> debitar(
            @Valid @RequestBody CadastroTransferenciaTO cadastroTransferenciaTO) throws Exception {
        Lancamento lancamento = lancamentoService.transferir(cadastroTransferenciaTO,
                userDetailsService.getUsuario().getConta());

        LancamentoTO lancamentoTO = (new LancamentoAssembler()).getData(lancamento);
        ResponseTO<LancamentoTO> responseTO = new ResponseTO<>(lancamentoTO);

        return ResponseEntity.ok(responseTO);
    }
}
