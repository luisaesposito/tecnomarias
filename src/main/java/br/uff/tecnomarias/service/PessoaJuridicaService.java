package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.entity.Avaliacao;
import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.repository.AvaliacaoRepository;
import br.uff.tecnomarias.domain.repository.PessoaJuridicaRepository;
import br.uff.tecnomarias.service.exception.EntidadeNaoEncontradaException;
import br.uff.tecnomarias.service.exception.PessoaInvalidaException;
import br.uff.tecnomarias.service.exception.PessoaJuridicaInvalidaException;
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
        validarPJ(pj);
        return pjRepository.saveAndFlush(pj);
    }

    @Transactional
    public PessoaJuridica alterar(Long id, @Valid PessoaJuridica pjAlterada) {
        validarPJ(pjAlterada);
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

    private void validarPJ(PessoaJuridica pj) {
        validarPessoa(pj);
        if (pj.getCnpj() == null || pj.getCnpj().isBlank())
            throw new PessoaJuridicaInvalidaException("CNPJ é obrigatório");
        if (pj.getCnpj().length() != 14)
            throw new PessoaJuridicaInvalidaException("CNPJ deve ter 14 caracteres");
        if (pj.getPorteEmpresa() == null)
            throw new PessoaJuridicaInvalidaException("PorteEmpresa é obrigatório");
        if (pj.getAreaAtuacao() == null || pj.getAreaAtuacao().isBlank())
            throw new PessoaJuridicaInvalidaException("AreaAtuacao é obrigatório");

    }

    private void validarPessoa(PessoaJuridica pj) {
        if (pj.getNome() == null || pj.getNome().isBlank())
            throw new PessoaInvalidaException("Nome é obrigatório");
        if (pj.getEmail() == null|| pj.getEmail().isBlank())
            throw new PessoaInvalidaException("Email é obrigatório");
        if (pj.getTipoPessoa() == null)
            throw new PessoaInvalidaException("TipoPessoa é obrigatório");
    }
}
