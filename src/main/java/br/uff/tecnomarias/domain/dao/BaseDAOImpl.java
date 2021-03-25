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

    @PersistenceContext(unitName = "tecnomariasPU")
    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public E buscarPorId(Long id) {
        return getEntityManager().find(clazz, id);
    }

    @Override
    public Optional<E> buscarPorIdOptional(Long id) {
        return Optional.ofNullable(getEntityManager().find(clazz, id));
    }

    @Override
    public List<E> buscarTodas() {
        String queryAll = String.format("select e from %s e ", clazz.getName());
        return getEntityManager().createQuery(queryAll).getResultList();
    }

    @Override
    public E salvar(E entidade) {
        try {
            getEntityManager().persist(entidade);
            getEntityManager().flush();
            return entidade;
        } catch (PersistenceException exception) {
            throw new DAOException(exception.getMessage(), exception);
        }
    }

    @Override
    public void remover(E entidade) {
        try {
            getEntityManager().remove(entidade);
            getEntityManager().flush();
        } catch (PersistenceException exception) {
            throw new DAOException(exception.getMessage(), exception);
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
