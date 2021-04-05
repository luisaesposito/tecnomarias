package br.uff.tecnomarias.domain.dao;

import br.uff.tecnomarias.domain.entity.Feedback;

import javax.inject.Inject;
import java.util.List;

public class FeedbackDAO extends BaseDAOImpl<Feedback> {

    @Inject
    public FeedbackDAO() {
        this.clazz = Feedback.class;
    }

    public List<Feedback> buscarRecentes() {
        return entityManager.createQuery("SELECT f FROM Feedback f ORDER BY f.id DESC")
                    .setMaxResults(3)
                    .getResultList();
    }
}
