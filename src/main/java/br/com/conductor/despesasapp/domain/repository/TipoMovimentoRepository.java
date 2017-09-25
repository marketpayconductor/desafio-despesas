package br.com.conductor.despesasapp.domain.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.conductor.despesasapp.domain.model.TipoMovimento;

@Transactional
@RepositoryRestResource(collectionResourceRel = "TipoMovimento", path = "TipoMovimento")
public interface TipoMovimentoRepository extends CrudRepository<TipoMovimento, Integer> {
	
	public TipoMovimento findByCodigoIdentificador(@Param(value = "codigoIdentificador") int codigoIdentificador);

}
