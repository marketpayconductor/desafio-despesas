package br.com.conductor.despesasapp.domain.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.conductor.despesasapp.domain.model.Conta;
import br.com.conductor.despesasapp.domain.model.Movimento;

@Transactional
@RepositoryRestResource(collectionResourceRel = "Movimento", path = "Movimento")
public interface MovimentoRepository extends CrudRepository<Movimento, Integer> {
	
	public List<Movimento> findByConta(@Param(value = "conta") Conta conta);
	
}
