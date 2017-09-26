package br.com.conductor.despesasapp.domain.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.conductor.despesasapp.domain.model.Conta;
import br.com.conductor.despesasapp.domain.model.Pessoa;

@Transactional
@RepositoryRestResource(collectionResourceRel = "Pessoa", path = "Pessoa")
public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {

	public Conta findByCpf(@Param(value = "cpf") String cpf);

}
