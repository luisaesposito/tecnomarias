package br.uff.tecnomarias.domain.dao;

import br.uff.tecnomarias.domain.entity.PessoaJuridica;

import javax.inject.Inject;

public class PessoaJuridicaDAO extends BaseDAOImpl<PessoaJuridica> {

    @Inject
    public PessoaJuridicaDAO() {
        this.clazz = PessoaJuridica.class;
    }
}
