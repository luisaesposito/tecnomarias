package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.dao.FeedbackDAO;
import br.uff.tecnomarias.domain.entity.Feedback;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@ManagedBean
public class FeedbackService {

    @Inject
    FeedbackDAO feedbackDAO;

    public Feedback salvar(@Valid final Feedback feedback) {
        return feedbackDAO.salvar(feedback);
    }

    public List<Feedback> buscarRecentes() {
        return feedbackDAO.buscarRecentes();
    }

}
