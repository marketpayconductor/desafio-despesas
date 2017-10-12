package br.pro.fagnerlima.desafiodespesas.presentation.transferobjectassembler;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import br.pro.fagnerlima.desafiodespesas.domain.model.conta.entity.Conta;
import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.entity.Lancamento;
import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.enumeration.TipoLancamento;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.CadastroLancamentoTO;

public class CadastroLancamentoAssemblerTest {

    @Test
    public void obterLancamento() {
        CadastroLancamentoTO cadastroLancamentoTO = new CadastroLancamentoTO(TipoLancamento.RECEITA, "Renda extra",
                BigDecimal.valueOf(500));
        Lancamento lancamento = (new CadastroLancamentoAssembler()).getEntity(cadastroLancamentoTO, new Conta());

        assertThat(lancamento, instanceOf(Lancamento.class));
        assertEquals(lancamento.getTipo(), cadastroLancamentoTO.getTipo());
        assertEquals(lancamento.getDescricao(), cadastroLancamentoTO.getDescricao());
        assertEquals(lancamento.getValor(), cadastroLancamentoTO.getValor());
    }

    @Test(expected = NullPointerException.class)
    public void lancarNullPointerException() {
        assertThat((new CadastroLancamentoAssembler()).getEntity(null, null), instanceOf(Lancamento.class));
    }
}
