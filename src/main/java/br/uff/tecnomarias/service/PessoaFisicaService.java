package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.entity.PessoaFisica;
import br.uff.tecnomarias.domain.repository.FeedbackRepository;
import br.uff.tecnomarias.domain.repository.PessoaFisicaRepository;
import br.uff.tecnomarias.service.exception.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
public class PessoaFisicaService {

    private static final String NOT_FOUND_MSG = "Pessoa nao encontrada";

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    PessoaFisicaRepository pfRepository;

    @Transactional
    public PessoaFisica salvar(@Valid PessoaFisica pf) {
        return pfRepository.save(pf);
    }

    @Transactional
    public PessoaFisica alterar(Long id, @Valid PessoaFisica pf) {
        PessoaFisica pfSalva = pfRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(NOT_FOUND_MSG));
        pfSalva.atualizarDados(pf);
        return pfRepository.save(pfSalva);
    }

    public PessoaFisica buscarPorId(Long id) {
        return pfRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(NOT_FOUND_MSG));
    }

    public List<PessoaFisica> buscarTodas() {
        return pfRepository.findAll();
    }

    @Transactional
    public void remover(final Long id) {
        PessoaFisica pf = pfRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(NOT_FOUND_MSG));
        if (pf.getFeedback() != null)
            feedbackRepository.delete(pf.getFeedback());
        pfRepository.delete(pf);
        pfRepository.flush();
    }

}
