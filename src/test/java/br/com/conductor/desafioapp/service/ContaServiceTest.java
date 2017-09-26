package br.com.conductor.desafioapp.service;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.conductor.despesasapp.domain.model.Conta;
import br.com.conductor.despesasapp.domain.model.Movimento;
import br.com.conductor.despesasapp.domain.repository.ContaRepository;
import br.com.conductor.despesasapp.domain.repository.MovimentoRepository;
import br.com.conductor.despesasapp.service.ContaService;

public class ContaServiceTest {

	@InjectMocks private ContaService contaService;
	@Mock private ContaRepository contaRepository;
	@Mock private MovimentoRepository movimentoRepository;
	
	@Before
	public void setupMock() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void validarAtualizacaoDeSaldoAposMovimentacao() throws Exception {
		Conta conta = new Conta(12345, BigDecimal.ZERO, new BigDecimal("100.0"));
		Movimento movimento = new Movimento
				.Builder("Teste de movimentação valor negativo", conta, new BigDecimal("50.0"))
				.despesa()
				.build();

		when(contaRepository.save(conta)).thenReturn(conta);
		when(movimentoRepository.save(movimento)).thenReturn(movimento);
		
		contaService.cadastrarMovimento(movimento);
		
		BigDecimal valorSaldoCorretoAposOperacao = new BigDecimal("50.0");
		Assert.assertEquals(conta.getSaldoAtual(), valorSaldoCorretoAposOperacao);
	}
	
	//TODO demais testes ...
}
