package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.dao.AvaliacaoDAO;
import br.uff.tecnomarias.domain.dao.PessoaJuridicaDAO;
import br.uff.tecnomarias.domain.entity.Avaliacao;
import br.uff.tecnomarias.domain.entity.PessoaJuridica;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.WebApplicationException;
import java.time.LocalDateTime;
import java.util.List;

@ManagedBean
public class PessoaJuridicaService {

    @Inject
    PessoaJuridicaDAO pjDAO;

    @Inject
    AvaliacaoDAO avaliacaoDAO;

    public PessoaJuridica salvar(@Valid PessoaJuridica pj) {
        return pjDAO.salvar(pj);
    }

    public PessoaJuridica alterar(Long id, @Valid PessoaJuridica pjAlterada) {
        PessoaJuridica pjSalva = pjDAO.buscarPorIdOptional(id)
                .orElseThrow(() -> new WebApplicationException("Vaga nao encontrada", 404));
        return pjSalva.atualizarDados(pjAlterada);
    }

    public PessoaJuridica buscarPorId(Long id) {
        return pjDAO.buscarPorIdOptional(id)
                .orElseThrow(() -> new WebApplicationException("Empresa não encontrada", 404));
    }

    public List<PessoaJuridica> buscarTodas() {
        return pjDAO.buscarTodas();
    }

    public Avaliacao avaliarEmpresa(Long idEmpresa, Avaliacao avaliacao) {
        PessoaJuridica pj = pjDAO.buscarPorIdOptional(idEmpresa)
                .orElseThrow(() -> new WebApplicationException("Empresa não encontrada", 400));
        avaliacao.setEmpresa(pj);
        avaliacao.setTimestamp(LocalDateTime.now());
        pj.addAvaliacao(avaliacao);
        pjDAO.salvar(pj);
        return avaliacao;
    }

}
