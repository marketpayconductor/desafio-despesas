package br.com.conductor.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.conductor.bean.Conta;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class TestContaRepository {
	
	@Autowired
	private ContaRepository repository;
	
	@Autowired
	private EntityManager manager;
	
	@Test
	public void testSalvarConta() {
		
		Conta conta = new Conta();
		conta.setNomeProprietario("Proprietario 1");
		conta.setCpf("11122233344");
		conta.setNumeroConta(new Long(123123));
		conta.setSaldo(new BigDecimal(50.25));
		Conta contaCadastrada = repository.save(conta);
		assertNotNull(contaCadastrada.getId());
	}
	
	@Test
	public void testfindByNumeroConta() {
		Conta conta = new Conta();
		conta.setNomeProprietario("Proprietario 1");
		conta.setCpf("11122233344");
		conta.setNumeroConta(new Long(123123));
		conta.setSaldo(new BigDecimal(50.25));
		manager.persist(conta);
		
		Conta contaCadastrada = repository.findByNumeroConta(new Long(123123));
		
		assertThat(contaCadastrada.getCpf().equals(conta.getCpf()));
		assertThat(contaCadastrada.getNumeroConta().equals(conta.getNumeroConta()));
	}


}
