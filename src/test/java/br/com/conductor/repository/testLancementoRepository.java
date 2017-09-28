package br.com.conductor.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.conductor.bean.Lancamento;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class testLancementoRepository {
	
	@Autowired
	private LancamentoRepository repository;
	
	@Autowired
	private EntityManager manager;
	
	@Test
	public void testSalvarLancamento() {
		Lancamento lancamento = new Lancamento();
		lancamento.setDataLancamento(new Date());
		lancamento.setDescricao("Transferencia");
		lancamento.setNumeroConta(new Long(333));
		lancamento.setValor(new BigDecimal(150));
		
		Lancamento salvo = repository.save(lancamento);
		assertNotNull(salvo.getId());
	}
	
	@Test
	public void testfindByNumeroConta() {
		Lancamento lancamento1 = new Lancamento();
		lancamento1.setDataLancamento(new Date());
		lancamento1.setDescricao("Transferencia");
		lancamento1.setNumeroConta(new Long(111));
		lancamento1.setValor(new BigDecimal(150));
		manager.persist(lancamento1);
		
		Lancamento lancamento2 = new Lancamento();
		lancamento2.setDataLancamento(new Date());
		lancamento2.setDescricao("Despesa");
		lancamento2.setNumeroConta(new Long(111));
		lancamento2.setValor(new BigDecimal(150).negate());
		manager.persist(lancamento2);
		
		Lancamento lancamento3 = new Lancamento();
		lancamento3.setDataLancamento(new Date());
		lancamento3.setDescricao("Receita");
		lancamento3.setNumeroConta(new Long(111));
		lancamento3.setValor(new BigDecimal(150));
		manager.persist(lancamento3);
		
		List<Lancamento> lista = new ArrayList<>();
		lista.add(lancamento1);
		lista.add(lancamento2);
		lista.add(lancamento3);
		
		List<Lancamento> listaEncontrada = repository.findByNumeroConta(new Long(111));
		
		assertNotNull(listaEncontrada);
		assertEquals(listaEncontrada, lista);
		assertThat(lista.equals(listaEncontrada));
		
	}

}
