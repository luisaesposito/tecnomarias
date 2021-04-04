package br.uff.tecnomarias.domain.dao;

import br.uff.tecnomarias.domain.entity.Feedback;

import javax.inject.Inject;
import javax.persistence.EntityTransaction;
import java.util.List;

public class FeedbackDAO extends BaseDAOImpl<Feedback> {

    @Inject
    public FeedbackDAO() {
        this.clazz = Feedback.class;
    }

    public List<Feedback> buscarRecentes() {
        EntityTransaction tx = getTransaction();
        List<Feedback> resultList;
        try {
            tx.begin();
            resultList = getEntityManager().createQuery("SELECT f FROM Feedback f ORDER BY f.id DESC")
                    .setMaxResults(3)
                    .getResultList();
            tx.commit();
        } catch (RuntimeException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            closeEM();
        }
        return resultList;
    }
}
