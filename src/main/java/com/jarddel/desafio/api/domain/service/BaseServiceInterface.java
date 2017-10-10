package com.jarddel.desafio.api.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseServiceInterface<T> {

    public T buscar(Long id);

    public Page<T> listarTodos(Pageable pageable);

    public T salvar(T entidade);

    public T atualizar(Long id, T entidade);

    public void remover(Long id);
}
