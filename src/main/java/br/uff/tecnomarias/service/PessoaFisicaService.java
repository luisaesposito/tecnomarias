package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.dao.PessoaFisicaDAO;
import br.uff.tecnomarias.domain.entity.PessoaFisica;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import java.util.List;

@ManagedBean
public class PessoaFisicaService {

    @Inject
    PessoaFisicaDAO pfDAO;


    public PessoaFisica buscarPorId(Long id) {
        return pfDAO.buscarPorIdOptional(id)
                .orElseThrow(() -> new WebApplicationException("Pessoa não encontrada", 404));
    }

    public List<PessoaFisica> buscarTodas() {
        return pfDAO.buscarTodas();
    }
}
