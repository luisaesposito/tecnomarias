package br.uff.tecnomarias.domain.dao;

import br.uff.tecnomarias.domain.entity.PessoaFisica;

import javax.inject.Inject;

public class PessoaFisicaDAO extends BaseDAOImpl<PessoaFisica> {

    @Inject
    public PessoaFisicaDAO() {
        this.clazz = PessoaFisica.class;
    }

}
