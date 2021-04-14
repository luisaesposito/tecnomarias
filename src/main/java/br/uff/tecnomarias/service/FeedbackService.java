package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.entity.Feedback;
import br.uff.tecnomarias.domain.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Transactional
    public Feedback salvar(@Valid final Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> buscarRecentes() {
        return feedbackRepository.findTop3ByOrderByIdDesc();
    }

}
