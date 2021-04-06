package br.uff.tecnomarias.domain.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Classe que encapsula as funcionalidades do EntityManager comuns a todas as entidades.
 * @param <E> Tipo da entidade
 */
@Stateless
public abstract class BaseDAOImpl<E> implements BaseDAO<E> {

    protected Class<E> clazz;
//    private EntityManagerFactory emFactory;
    @PersistenceContext(unitName = "tecnomariasPU")
    protected EntityManager entityManager;

    @Override
    public E buscarPorId(Long id) {
        E entidade = entityManager.find(clazz, id);
        return entidade;
    }

    @Override
    public Optional<E> buscarPorIdOptional(Long id) {
        return Optional.ofNullable(buscarPorId(id));
    }

    @Override
    public List<E> buscarTodas() {
        String queryAll = String.format("SELECT e FROM %s e ", clazz.getName());
        List<E> resultList = entityManager.createQuery(queryAll).getResultList();;
        return resultList;
    }

    @Override
    public int count() {
        // TODO ajustar count
        String queryCountAll = String.format("SELECT COUNT(e) FROM %s e ", clazz.getName());
        return ((Long) entityManager.createQuery(queryCountAll).getSingleResult()).intValue();
    }

    @Override
    public E salvar(E entidade) {
        entityManager.persist(entidade);
        return entidade;
    }

    @Override
    public void remover(E entidade) {
        entityManager.remove(entidade);
    }

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
