package br.com.conductor.despesasapp.domain.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.conductor.despesasapp.domain.model.Conta;

@Transactional
@RepositoryRestResource(collectionResourceRel = "Conta", path = "Conta")
public interface ContaRepository extends CrudRepository<Conta, Integer> {
	
	public Conta findByNumero(@Param(value = "numero") long numero);
	
}
