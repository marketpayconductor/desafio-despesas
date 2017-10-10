package com.jarddel.desafio.api.application.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.jarddel.desafio.api.application.service.exception.InformacaoNaoEncontradaException;
import com.jarddel.desafio.api.domain.service.BaseServiceInterface;
import com.jarddel.desafio.api.infrastructure.service.ApplicationUserDetailsService;

@Service
public abstract class BaseService<T> implements BaseServiceInterface<T> {

    @Autowired
    protected ApplicationUserDetailsService userDetailsService;

    @Override
    public T buscar(Long id) {
        T entidade = getRepository().findOne(id);

        if (entidade == null) {
            throw new InformacaoNaoEncontradaException();
        }

        return entidade;
    }

    public Page<T> listarTodos(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public T salvar(T entidade) {
        return getRepository().save(entidade);
    }

    @Override
    public T atualizar(Long id, T entidade) {
        T entidadeSalva = buscar(id);
        BeanUtils.copyProperties(entidade, entidadeSalva);
        return salvar(entidadeSalva);
    }

    @Override
    public void remover(Long id) {
        getRepository().delete(id);
    }

    protected abstract JpaRepository<T, Long> getRepository();

}
