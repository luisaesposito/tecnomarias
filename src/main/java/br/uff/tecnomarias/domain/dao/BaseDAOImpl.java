package br.uff.tecnomarias.domain.dao;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

/**
 * Classe que encapsula as funcionalidades do EntityManager comuns a todas as entidades.
 * @param <E> Tipo da entidade
 */
public abstract class BaseDAOImpl<E> implements BaseDAO<E> {

    protected Class<E> clazz;
    private EntityManagerFactory emFactory;
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        if (emFactory == null || !emFactory.isOpen())
            emFactory = Persistence.createEntityManagerFactory("tecnomariasPU");
        if (this.entityManager == null)
            this.entityManager = emFactory.createEntityManager();
        return this.entityManager;
    }

    protected void closeEM() {
        if (this.entityManager != null && this.entityManager.isOpen()) {
            this.entityManager.close();
            this.emFactory.close();
        }
    }
    protected EntityTransaction getTransaction() {
        return getEntityManager().getTransaction();
    }

    @Override
    public E buscarPorId(Long id) {
        EntityTransaction tx = getTransaction();
        E entidade;
        try {
            tx.begin();
            entidade = getEntityManager().find(clazz, id);
            tx.commit();
        } catch (RuntimeException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            closeEM();
        }
        return entidade;
    }

    @Override
    public Optional<E> buscarPorIdOptional(Long id) {
        return Optional.ofNullable(buscarPorId(id));
    }

    @Override
    public List<E> buscarTodas() {
        String queryAll = String.format("SELECT e FROM %s e ", clazz.getName());
        EntityTransaction tx = getTransaction();
        List<E> resultList;
        try {
            tx.begin();
            resultList = getEntityManager().createQuery(queryAll).getResultList();
            tx.commit();
        } catch (RuntimeException e) {
            throw new DAOException(e.getMessage(),e);
        } finally{
            closeEM();
        }
        return resultList;
    }

    @Override
    public int count() {
        String queryCountAll = String.format("SELECT COUNT(e) FROM %s e ", clazz.getName());
        EntityTransaction tx = getTransaction();
        int count;
        try {
            tx.begin();
            count = (int) getEntityManager().createQuery(queryCountAll).getSingleResult();
            tx.commit();
        } catch (RuntimeException e) {
            throw new DAOException(e.getMessage(), e);
        } finally{
            closeEM();
        }
        return count;
    }

    @Override
    public E salvar(E entidade) {
        EntityTransaction tx = getTransaction();
        try {
            tx.begin();
            getEntityManager().persist(entidade);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            throw new DAOException(e.getMessage(), e);
        } finally {
            closeEM();
        }
        return entidade;
    }

    @Override
    public void remover(E entidade) {
        EntityTransaction tx = getTransaction();
        try {
            tx.begin();
            getEntityManager().remove(entidade);
            tx.commit();
        } catch (RuntimeException e) {
            throw new DAOException(e.getMessage(),e);
        } finally{
            closeEM();
        }
    }

    @Override
    public E merge(E entidade) {
        try {
            E entidadePersistida = getEntityManager().merge(entidade);
            getEntityManager().flush();
            return entidadePersistida;
        } catch (PersistenceException exception) {
            throw new DAOException(exception.getMessage(), exception);
        }
    }

    @Override
    public void refresh(E entidade) {
        getEntityManager().refresh(entidade);
    }

    @Override
    public void flush() {
        getEntityManager().flush();
    }

    // TODO implementar paginacao
//    protected void setPaginacao(Query query, Paginacao paginacao) {
//        if (paginacao.getTamanhoPagina() != null) {
//            query.setMaxResults(paginacao.getTamanhoPagina());
//
//            if (paginacao.getFirstResult() != null) {
//                query.setFirstResult(paginacao.getFirstResult());
//            }
//        }
//    }

//    private void beginTransaction() {
//        try {
//            getEntityManager().getTransaction().begin();
//        } catch (IllegalStateException e) {
//            rollBackTransaction();
//        }
//    }
//
//    private void commitTransaction() {
//        try {
//            getEntityManager().getTransaction().commit();
//        } catch (IllegalStateException | RollbackException e) {
//            rollBackTransaction();
//        }
//    }
//
//    private void rollBackTransaction() {
//        try {
//            getEntityManager().getTransaction().rollback();
//        } catch (IllegalStateException | PersistenceException e) {
//            e.printStackTrace();
//        }
//    }

}
