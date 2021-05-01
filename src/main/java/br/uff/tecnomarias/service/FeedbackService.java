package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.entity.Feedback;
import br.uff.tecnomarias.domain.entity.PessoaFisica;
import br.uff.tecnomarias.domain.repository.FeedbackRepository;
import br.uff.tecnomarias.domain.repository.PessoaFisicaRepository;
import br.uff.tecnomarias.service.exception.BadRequestException;
import br.uff.tecnomarias.service.exception.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Service
public class FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    PessoaFisicaRepository pfRepository;

    public List<Feedback> buscarRecentes() {
        return feedbackRepository.findTop3ByOrderByIdDesc();
    }

    @Transactional
    public Feedback salvarFeedback(Long idAvaliadora, Feedback feedback) {
        PessoaFisica pf = pfRepository.findById(idAvaliadora)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pessoa não encontrada"));
        feedback.setPessoa(pf);
        feedbackRepository.save(feedback);
        return feedback;
    }

    @Transactional
    public void remover(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Feedback nao encontrado"));
        PessoaFisica pf = feedback.getPessoa();
        pf.setFeedback(null);
        pfRepository.save(pf);
        feedbackRepository.delete(feedback);
    }

}
