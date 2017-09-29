package br.com.conductor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.conductor.bean.Lancamento;
import java.lang.Long;
import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	
	List<Lancamento> findByNumeroConta(Long numeroconta);
}
