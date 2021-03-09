package br.uff.tecnomarias.domain.dao;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface BaseDAO<E> {

    E buscarPorId(Long id);
    Optional<E> buscarPorIdOptional(Long id);
    List<E> buscarTodas();
    void salvar(E entidade);
    void remover(Long id);
    void remover(E entidade);
    void merge(E entidade);
    void flush();
    EntityManager getEntityManager();
}
