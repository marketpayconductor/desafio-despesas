package br.pro.fagnerlima.desafiodespesas.application.service;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.pro.fagnerlima.desafiodespesas.domain.service.BaseService;
import br.pro.fagnerlima.desafiodespesas.infrastructure.persistence.hibernate.repository.BaseRepository;

@Service
public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public T findOne(ID id) throws EmptyResultDataAccessException {
        T entity = getRepository().findOne(id);

        if (entity == null) {
            throw new EmptyResultDataAccessException(1);
        }

        return entity;
    }

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public T update(ID id, T entity) {
        T entitySaved = findOne(id);
        BeanUtils.copyProperties(entity, entitySaved, "id");

        return save(entitySaved);
    }

    @Override
    public void delete(ID id) {
        getRepository().delete(id);
    }

    /**
     * Retorna o repositório da entidade.
     * 
     * @return Repositório da entidade.
     */
    protected abstract BaseRepository<T, ID> getRepository();
}
