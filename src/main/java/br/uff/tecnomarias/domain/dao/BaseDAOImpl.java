package br.uff.tecnomarias.domain.dao;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class BaseDAOImpl<E> implements BaseDAO<E>{

    @PersistenceContext(unitName = "tecnomariasPU")
    private EntityManager entityManager;
    protected final Class<E> clazz;

    public BaseDAOImpl(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Override
    public E buscarPorId(Long id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public Optional<E> buscarPorIdOptional(Long id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Override
    public List<E> buscarTodas() {
        String queryAll = String.format("select e from %s e ",clazz.getName());
        return entityManager.createQuery(queryAll).getResultList();
    }

    @Override
    public void salvar(E entidade) {
        beginTransaction();
        entityManager.persist(entidade);
        commitTransaction();
    }

    @Override
    public void remover(Long id) {
        E entidade = buscarPorId(id);
        if (entidade == null)
            throw new EntityNotFoundException();
        // TODO ler sobre EntityNotFoundException
        beginTransaction();
        entityManager.remove(entidade);
        commitTransaction();
    }

    @Override
    public void remover(E entidade) {
        beginTransaction();
        entityManager.remove(entidade);
        commitTransaction();
    }


    @Override
    public void merge(E entidade) {
        entityManager.merge(entidade);
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    private void beginTransaction() {
        try {
            entityManager.getTransaction().begin();
        } catch (IllegalStateException e) {
            rollBackTransaction();
        }
    }

    private void commitTransaction() {
        try {
            entityManager.getTransaction().commit();
        } catch (IllegalStateException | RollbackException e) {
            rollBackTransaction();
        }
    }

    private void rollBackTransaction() {
        try {
            entityManager.getTransaction().rollback();
        } catch (IllegalStateException | PersistenceException e) {
            e.printStackTrace();
        }
    }

}
