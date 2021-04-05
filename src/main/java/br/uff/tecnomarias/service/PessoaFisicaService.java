package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.dao.PessoaFisicaDAO;
import br.uff.tecnomarias.domain.entity.PessoaFisica;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.WebApplicationException;
import java.util.List;

@ManagedBean
public class PessoaFisicaService {

    @Inject
    PessoaFisicaDAO pfDAO;

    public PessoaFisica salvar(@Valid PessoaFisica pf) {
        return pfDAO.salvar(pf);
    }

    public PessoaFisica alterar(Long id, @Valid PessoaFisica pfAlterada) {
        PessoaFisica pfSalva = pfDAO.buscarPorIdOptional(id)
                .orElseThrow(() -> new WebApplicationException("Pessoa nao encontrada", 404));
        return pfSalva.atualizarDados(pfAlterada);
    }

    public PessoaFisica buscarPorId(Long id) {
        return pfDAO.buscarPorIdOptional(id)
                .orElseThrow(() -> new WebApplicationException("Pessoa não encontrada", 404));
    }

    public List<PessoaFisica> buscarTodas() {
        return pfDAO.buscarTodas();
    }
}
