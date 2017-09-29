package br.com.conductor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.conductor.bean.Conta;
import br.com.conductor.repository.ContaRepository;
import br.com.conductor.service.ContaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testContaService {

	@Autowired
	private ContaService service;

	@Autowired
	private ContaRepository repository;

	@Test
	public void adicionarDespesa() throws Exception {
		BigDecimal valor = new BigDecimal(10);

		Conta conta = new Conta();
		conta.setNomeProprietario("Proprietario 1");
		conta.setCpf("11122233344");
		conta.setNumeroConta(new Long(790));
		conta.setSaldo(new BigDecimal(50));
		repository.save(conta);

		service.adicionarDespesa(valor, conta.getNumeroConta());
		Conta contaDespesa = repository.findByNumeroConta(conta.getNumeroConta());
		BigDecimal valorDespesa = conta.getSaldo().subtract(valor);
		assertEquals(contaDespesa.getSaldo().setScale(2, RoundingMode.HALF_EVEN),
				valorDespesa.setScale(2, RoundingMode.HALF_EVEN));
	}

	@Test
	public void adicionarReceita() {
		BigDecimal valor = new BigDecimal(10);
		
		Conta conta = new Conta();
		conta.setNomeProprietario("Proprietario 1");
		conta.setCpf("11122233344");
		conta.setNumeroConta(new Long(791));
		conta.setSaldo(new BigDecimal(50));
		repository.save(conta);
		
		service.adicionarReceita(valor, conta.getNumeroConta());
		Conta contaDespesa = repository.findByNumeroConta(conta.getNumeroConta());
		BigDecimal valorReceita = conta.getSaldo().add(valor);
		
		assertEquals(contaDespesa.getSaldo().setScale(2, RoundingMode.HALF_EVEN),
				valorReceita.setScale(2, RoundingMode.HALF_EVEN));
		
	}
	
	@Test
	public void consultarSaldoAtual() {
		Conta conta = new Conta();
		conta.setNomeProprietario("Proprietario 10");
		conta.setCpf("11122233344");
		conta.setNumeroConta(new Long(793));
		conta.setSaldo(new BigDecimal(50));
		repository.save(conta);
		
		service.consultarSaldoAtual(conta.getNumeroConta());
		Conta contaSaldo = repository.findByNumeroConta(conta.getNumeroConta());
		
		assertEquals(contaSaldo.getSaldo().setScale(2, RoundingMode.HALF_EVEN), 
				conta.getSaldo().setScale(2, RoundingMode.HALF_EVEN));
	}
	
	@Test
	public void transferirCredito() {
		Conta conta1 = new Conta();
		conta1.setNomeProprietario("Proprietario 10");
		conta1.setCpf("11122233344");
		conta1.setNumeroConta(new Long(796));
		conta1.setSaldo(new BigDecimal(50));
		repository.save(conta1);
		
		Conta conta2 = new Conta();
		conta2.setNomeProprietario("Proprietario 11");
		conta2.setCpf("11122233344");
		conta2.setNumeroConta(new Long(797));
		conta2.setSaldo(new BigDecimal(50));
		repository.save(conta2);
		
		BigDecimal valorTransferido = new BigDecimal(20);
		
		service.transferirCredito(conta1.getNumeroConta(), conta2.getNumeroConta(), valorTransferido);
		Conta contaDebitada = repository.findByNumeroConta(conta1.getNumeroConta());
		Conta contaCreditada = repository.findByNumeroConta(conta2.getNumeroConta());
		
		BigDecimal saldoDebitado = conta1.getSaldo().subtract(valorTransferido);
		BigDecimal saldoCreditado = conta2.getSaldo().add(valorTransferido);
		
		assertEquals(contaDebitada.getSaldo().setScale(2, RoundingMode.HALF_EVEN), 
				saldoDebitado.setScale(2, RoundingMode.HALF_EVEN));
		assertEquals(contaCreditada.getSaldo().setScale(2, RoundingMode.HALF_EVEN), 
				saldoCreditado.setScale(2, RoundingMode.HALF_EVEN));
		
	}

}
