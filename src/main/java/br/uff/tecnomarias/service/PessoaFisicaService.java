package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.entity.PessoaFisica;
import br.uff.tecnomarias.domain.repository.AvaliacaoRepository;
import br.uff.tecnomarias.domain.repository.FeedbackRepository;
import br.uff.tecnomarias.domain.repository.PessoaFisicaRepository;
import br.uff.tecnomarias.service.exception.EntidadeNaoEncontradaException;
import br.uff.tecnomarias.service.exception.PessoaInvalidaException;
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
    AvaliacaoRepository avaliacaoRepository;
    @Autowired
    PessoaFisicaRepository pfRepository;

    @Transactional
    public PessoaFisica salvar(@Valid PessoaFisica pf) {
        validarPessoa(pf);
        return pfRepository.save(pf);
    }

    @Transactional
    public PessoaFisica alterar(Long id, @Valid PessoaFisica pf) {
        validarPessoa(pf);
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
        avaliacaoRepository.findByAvaliadora(pf).ifPresent(av -> avaliacaoRepository.delete(av));
        pfRepository.delete(pf);
        pfRepository.flush();
    }

    private void validarPessoa(PessoaFisica pf) {
        if (pf.getNome() == null || pf.getNome().isBlank())
            throw new PessoaInvalidaException("Nome é obrigatório");
        if (pf.getEmail() == null || pf.getEmail().isBlank())
            throw new PessoaInvalidaException("Email é obrigatório");
        if (pf.getTipoPessoa() == null)
            throw new PessoaInvalidaException("TipoPessoa é obrigatório");
    }
}
