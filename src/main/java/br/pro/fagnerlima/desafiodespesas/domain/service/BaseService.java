package br.pro.fagnerlima.desafiodespesas.domain.service;

import java.io.Serializable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface base para os serviços da aplicação.
 * 
 * @author Fagner Lima
 *
 * @param <T>
 * @param <ID>
 */
public interface BaseService<T, ID extends Serializable> {

    /**
     * Retorna todas as instâncias do tipo T de acordo com os filtros.
     * 
     * @param pageable
     * @return As instâncias encontradas.
     */
    public Page<T> findAll(Pageable pageable);

    /**
     * Retorna uma entidade pelo seu ID.
     * 
     * @param id
     * @return A instância encontrada.
     */
    public T findOne(ID id);

    /**
     * Salva uma entidade.
     * 
     * @param entity
     * @return A entidade salva.
     */
    public T save(T entity);

    /**
     * Atualiza a entidade pelo seu ID.
     * 
     * @param id
     * @param entity
     * @return A entidade atualizada.
     */
    public T update(ID id, T entity);

    /**
     * Remove uma entidade.
     * 
     * @param id
     */
    public void delete(ID id);
}
