package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.entity.Avaliacao;
import br.uff.tecnomarias.domain.entity.PessoaFisica;
import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.repository.AvaliacaoRepository;
import br.uff.tecnomarias.domain.repository.PessoaFisicaRepository;
import br.uff.tecnomarias.domain.repository.PessoaJuridicaRepository;
import br.uff.tecnomarias.service.exception.BadRequestException;
import br.uff.tecnomarias.service.exception.EntidadeNaoEncontradaException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class PessoaJuridicaService {

    private final PessoaJuridicaRepository pjRepository;
    private final AvaliacaoRepository avaliacaoRepository;
    private final PessoaFisicaRepository pfRepository;

    public PessoaJuridicaService(PessoaJuridicaRepository pjRepository,
                                 AvaliacaoRepository avaliacaoRepository,
                                 PessoaFisicaRepository pfRepository) {
        Objects.requireNonNull(pjRepository);
        Objects.requireNonNull(avaliacaoRepository);
        Objects.requireNonNull(pfRepository);
        this.pjRepository = pjRepository;
        this.avaliacaoRepository = avaliacaoRepository;
        this.pfRepository = pfRepository;
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
        PessoaFisica avaliadora = pfRepository.findById(avaliacao.getAvaliadora().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Avaliadora não encontrada"));

        if (avaliadora.getDataCadastro().isAfter(LocalDate.now().minusMonths(3)))
            throw new BadRequestException("Usuária deve estar cadastrada há 3 meses para avaliar empresa");

        if (avaliacaoRepository.findByAvaliadora(avaliadora).isPresent())
            throw new BadRequestException("Usuária já avaliou empresa");

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
