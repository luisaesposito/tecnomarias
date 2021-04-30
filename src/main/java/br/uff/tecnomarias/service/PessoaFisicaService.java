package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.entity.Feedback;
import br.uff.tecnomarias.domain.entity.PessoaFisica;
import br.uff.tecnomarias.domain.entity.PessoaJuridica;
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
public class PessoaFisicaService {

    @Autowired
    PessoaFisicaRepository pfRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    @Transactional
    public PessoaFisica salvar(@Valid PessoaFisica pf) {
        return pfRepository.save(pf);
    }

    @Transactional
    public PessoaFisica alterar(Long id, @Valid PessoaFisica pf) {
        PessoaFisica pfSalva = pfRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pessoa nao encontrada"));
        pfSalva.atualizarDados(pf);
        return pfRepository.save(pfSalva);
    }

    public PessoaFisica buscarPorId(Long id) {
        return pfRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pessoa não encontrada"));
    }

    public List<PessoaFisica> buscarTodas() {
        return pfRepository.findAll();
    }

    @Transactional
    public void remover(final Long id) {
        PessoaFisica pf = pfRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pessoa nao encontrada"));
        pfRepository.delete(pf);
    }

    @Transactional
    public Feedback salvarFeedback(Long idAvaliadora, Feedback feedback) {
        PessoaFisica pf = pfRepository.findById(idAvaliadora)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pessoa não encontrada"));
        if (Objects.isNull(pf.getFeedback())) {
            pf.setFeedback(feedback);
            pfRepository.save(pf);
            pfRepository.flush();
        } else {
            throw new BadRequestException("O usuário já relatou seu feedback");
        }
        return feedbackRepository.findTop1ByOrderByIdDesc();
    }

}
