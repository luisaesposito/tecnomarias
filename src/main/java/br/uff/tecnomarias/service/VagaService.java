package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.entity.Vaga;
import br.uff.tecnomarias.domain.repository.PessoaJuridicaRepository;
import br.uff.tecnomarias.domain.repository.VagaRepository;
import br.uff.tecnomarias.service.exception.BadRequestException;
import br.uff.tecnomarias.service.exception.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class VagaService {

    @Autowired
    VagaRepository vagaRepository;

    @Autowired
    PessoaJuridicaRepository pjRepository;

    @Transactional
    public Vaga salvar(@Valid Vaga vaga) {
        PessoaJuridica pj = pjRepository.findById(vaga.getEmpresa().getId())
                .orElseThrow(() -> new BadRequestException("Empresa nao encontrada"));
        vaga.setEmpresa(pj);
        vagaRepository.save(vaga);
        return vaga;
    }

    public Vaga buscarPorId(final Long id) {
        return vagaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Vaga nao encontrada"));
    }

    public Long count() {
        return vagaRepository.count();
    }

    public List<Vaga> buscarTodas() {
        return vagaRepository.findAll();
    }

    public List<Vaga> buscarPorEmpresa(final Long idEmpresa) {
        PessoaJuridica empresa = pjRepository.findById(idEmpresa)
                .orElseThrow(() -> new BadRequestException("Empresa nao encontrada"));
        return vagaRepository.findByEmpresa(empresa);
    }

    public List<Vaga> buscarPorAreaAtuacao(String areaAtuacao) {
        return vagaRepository.findByAreaAtuacao(areaAtuacao);
    }

    public List<Vaga> buscarPorCargo(String cargo) {
        return vagaRepository.findByCargo(cargo);
    }

    public List<Vaga> buscarPorLocalidade(String localidade) {
        return vagaRepository.findByLocalidade(localidade);
    }

    @Transactional
    public Vaga alterar(final Long id, @Valid final Vaga vagaAlterada) {
        Vaga vagaSalva = vagaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Vaga nao encontrada"));
        return vagaRepository.save(vagaSalva.atualizarDados(vagaAlterada));
    }

    @Transactional
    public void remover(final Long id) {
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Vaga nao encontrada"));
        vagaRepository.delete(vaga);
    }

    public List<String> listarAreaAtuacao() {
        return vagaRepository.listAreaAtuacao();
    }

}
