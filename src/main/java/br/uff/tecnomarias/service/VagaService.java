package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.entity.Vaga;
import br.uff.tecnomarias.domain.enums.Cargo;
import br.uff.tecnomarias.domain.repository.PessoaJuridicaRepository;
import br.uff.tecnomarias.domain.repository.VagaRepository;
import br.uff.tecnomarias.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
public class VagaService {

    @Autowired
    VagaRepository vagaRepository;

    @Autowired
    PessoaJuridicaRepository pjRepository;

    private static final String VAGA_NOT_FOUND = "Vaga nao encontrada";
    private static final String EMPRESA_NOT_FOUND = "Empresa nao encontrada";

    @Transactional
    public Vaga salvar(@Valid Vaga vaga) {
        validarVaga(vaga);
        PessoaJuridica pj = pjRepository.findById(vaga.getEmpresa().getId())
                .orElseThrow(() -> new BadRequestException(EMPRESA_NOT_FOUND));
        pj.addVaga(vaga);
        pjRepository.save(pj);
        return pj.getVagas().get(pj.getVagas().size() - 1);
    }

    public Vaga buscarPorId(final Long id) {
        return vagaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(VAGA_NOT_FOUND));
    }

    public Long count() {
        return vagaRepository.count();
    }

    public List<Vaga> buscarTodas() {
        return vagaRepository.findAll();
    }

    public List<Vaga> buscarPorEmpresa(final Long idEmpresa) {
        PessoaJuridica empresa = pjRepository.findById(idEmpresa)
                .orElseThrow(() -> new BadRequestException(EMPRESA_NOT_FOUND));
        return vagaRepository.findByEmpresa(empresa);
    }

    public List<Vaga> buscarPorAreaAtuacao(String areaAtuacao) {
        return vagaRepository.findByAreaAtuacaoContainingIgnoreCase(areaAtuacao);
    }

    public List<Vaga> buscarPorCargo(Cargo cargo) {
        return vagaRepository.findByCargo(cargo);
    }

    public List<Vaga> buscarPorLocalidade(String localidade) {
        return vagaRepository.findByLocalidade(localidade);
    }

    @Transactional
    public Vaga alterar(final Long id, @Valid final Vaga vagaAlterada) {
        validarVaga(vagaAlterada);
        Vaga vagaSalva = vagaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(VAGA_NOT_FOUND));
        return vagaRepository.save(vagaSalva.atualizarDados(vagaAlterada));
    }

    @Transactional
    public void remover(final Long id) {
        var vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(VAGA_NOT_FOUND));
        vaga.getEmpresa().removeVaga(vaga);
        vagaRepository.delete(vaga);
    }

    public List<String> listarAreaAtuacao() {
        return vagaRepository.listAreaAtuacao();
    }

    private void validarVaga(Vaga vg) {
        if (vg.getAreaAtuacao() == null || vg.getAreaAtuacao().isBlank())
            throw new VagaInvalidaException("Área Atuação é obrigatório");
        if (vg.getDescricao() == null|| vg.getDescricao().isBlank())
            throw new VagaInvalidaException("Descrição é obrigatório");
        if (vg.getCargo() == null)
            throw new VagaInvalidaException("Cargo é obrigatório");
    }

}
