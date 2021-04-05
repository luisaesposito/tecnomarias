package br.uff.tecnomarias.domain.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<E> {

    E buscarPorId(Long id);
    Optional<E> buscarPorIdOptional(Long id);
    List<E> buscarTodas();
    int count();
    E salvar(E entidade);
    void remover(Long id);
    E merge(E entidade);
    void refresh(E entidade);
    void flush();
}
