package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.dao.BaseDAO;
import br.uff.tecnomarias.domain.dao.PessoaJuridicaDAO;
import br.uff.tecnomarias.domain.dao.VagaDAO;
import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.entity.Vaga;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.WebApplicationException;
import java.util.List;

@ManagedBean
public class VagaService {

    @Inject
    VagaDAO vagaDAO;

    @Inject
    PessoaJuridicaDAO pjDAO;

    public Vaga salvar(@Valid Vaga vaga) {
        vagaDAO.salvar(vaga);
        return vaga;
    }

    public List<Vaga> buscarPorEmpresa(String cnpj) {
        PessoaJuridica empresa = pjDAO.buscarPorCNPJ(cnpj)
                .orElseThrow(() -> new WebApplicationException("Empresa nao encontrada", 400));
        return vagaDAO.buscarPorEmpresa(empresa);
    }

}
