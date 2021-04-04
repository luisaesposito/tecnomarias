package br.uff.tecnomarias.domain.dao;

import br.uff.tecnomarias.domain.entity.Avaliacao;

import javax.inject.Inject;

public class AvaliacaoDAO extends BaseDAOImpl<Avaliacao> {

    @Inject
    public AvaliacaoDAO() {
        this.clazz = Avaliacao.class;
    }
}
