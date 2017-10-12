package br.pro.fagnerlima.desafiodespesas.presentation.transferobjectassembler;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import br.pro.fagnerlima.desafiodespesas.domain.model.conta.entity.Conta;
import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.entity.Lancamento;
import br.pro.fagnerlima.desafiodespesas.domain.model.lancamento.enumeration.TipoLancamento;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.LancamentoTO;

public class LancamentoAssemblerTest {

    @Test
    public void obterLancamentoTO() {
        Lancamento lancamento = new Lancamento(TipoLancamento.RECEITA, new Conta(), BigDecimal.TEN, "My description");
        LancamentoTO lancamentoTO = (new LancamentoAssembler()).getData(lancamento);

        assertThat(lancamentoTO, instanceOf(LancamentoTO.class));
        assertEquals(lancamentoTO.getId(), lancamento.getId());
        assertEquals(lancamentoTO.getData(), lancamento.getDataCriacao());
        assertEquals(lancamentoTO.getTipo(), lancamento.getTipo());
        assertEquals(lancamentoTO.getDescricao(), lancamento.getDescricao());
        assertEquals(lancamentoTO.getValor(), lancamento.getValor());
    }

    @Test(expected = NullPointerException.class)
    public void lancarNullPointerException() {
        assertEquals((new LancamentoAssembler()).getData(null), instanceOf(LancamentoTO.class));
    }
}
