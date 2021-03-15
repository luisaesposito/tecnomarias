package br.uff.tecnomarias.domain.dao;

import br.uff.tecnomarias.domain.entity.PessoaJuridica;

import javax.inject.Inject;
import java.util.Optional;

public class PessoaJuridicaDAO extends BaseDAOImpl<PessoaJuridica> {

    @Inject
    public PessoaJuridicaDAO(Class<PessoaJuridica> clazz) {
        super(clazz);
    }

    public Optional<PessoaJuridica> buscarPorCNPJ(String cnpj) {
        return Optional.ofNullable(getEntityManager().createNamedQuery("Vaga.findByEmpresa", this.clazz).getSingleResult());
    }
}
