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

@Service
public class PessoaJuridicaService {

    @Autowired
    PessoaJuridicaRepository pjRepository;

    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    @Transactional
    public PessoaJuridica salvar(@Valid PessoaJuridica pj) {
        return pjRepository.save(pj);
    }

    @Transactional
    public PessoaJuridica alterar(Long id, @Valid PessoaJuridica pjAlterada) {
        PessoaJuridica pjSalva = pjRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Vaga nao encontrada"));
        return pjSalva.atualizarDados(pjAlterada);
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
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Vaga nao encontrada"));
        pjRepository.delete(pj);
    }

    @Transactional
    public Avaliacao avaliarEmpresa(Long idEmpresa, Avaliacao avaliacao) {
        PessoaJuridica pj = pjRepository.findById(idEmpresa)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Empresa não encontrada"));
        avaliacao.setEmpresa(pj);
        avaliacao.setTimestamp(LocalDateTime.now());
        pj.addAvaliacao(avaliacao);
        pjRepository.save(pj);
        pjRepository.flush();
        return avaliacaoRepository.findTopByOrderByTimestampDesc();
    }
}
