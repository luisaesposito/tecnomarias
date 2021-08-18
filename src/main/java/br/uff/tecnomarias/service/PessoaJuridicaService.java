package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.entity.Avaliacao;
import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.repository.AvaliacaoRepository;
import br.uff.tecnomarias.domain.repository.PessoaJuridicaRepository;
import br.uff.tecnomarias.service.exception.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class PessoaJuridicaService {

    private final PessoaJuridicaRepository pjRepository;
    private final AvaliacaoRepository avaliacaoRepository;

    public PessoaJuridicaService(PessoaJuridicaRepository pjRepository,
                                 AvaliacaoRepository avaliacaoRepository) {
        Objects.requireNonNull(pjRepository);
        Objects.requireNonNull(avaliacaoRepository);
        this.pjRepository = pjRepository;
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @Transactional
    public PessoaJuridica salvar(@Valid PessoaJuridica pj) {
        return pjRepository.save(pj);
    }

    @Transactional
    public PessoaJuridica alterar(Long id, @Valid PessoaJuridica pjAlterada) {
        PessoaJuridica pjSalva = pjRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Empresa nao encontrada"));
        pjSalva.atualizarDados(pjAlterada);
        return pjRepository.save(pjSalva);
    }

    public PessoaJuridica buscarPorId(Long id) {
        return pjRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Empresa não encontrada"));
    }

    public List<PessoaJuridica> buscarTodas() {
        return pjRepository.findAll();
    }

    @Transactional
    public void remover(final Long id) {
        PessoaJuridica pj = pjRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Empresa nao encontrada"));
        pjRepository.delete(pj);
    }

    @Transactional
    public Avaliacao avaliarEmpresa(Long idEmpresa, Avaliacao avaliacao) {
        PessoaJuridica pj = pjRepository.findById(idEmpresa)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Empresa não encontrada"));
        avaliacao.setEmpresa(pj);
        avaliacao.setData(LocalDateTime.now());
        pj.addAvaliacao(avaliacao);
        pjRepository.save(pj);
        pjRepository.flush();
        return avaliacaoRepository.findTopByOrderByDataDesc();
    }

    @Transactional
    public void removerAvaliacao(Long idAvaliacao) {
        Avaliacao av = avaliacaoRepository.findById(idAvaliacao)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Avaliacao nao encontrada"));
        avaliacaoRepository.delete(av);
    }
}
