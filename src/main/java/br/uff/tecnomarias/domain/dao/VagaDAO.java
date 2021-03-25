package br.uff.tecnomarias.domain.dao;

import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.entity.Vaga;

import javax.inject.Inject;
import java.util.List;

public class VagaDAO extends BaseDAOImpl<Vaga> {

    @Inject
    public VagaDAO() {
        this.clazz = Vaga.class;
    }

    public List<Vaga> buscarPorEmpresa(PessoaJuridica empresa) {
        return getEntityManager().createNamedQuery("Vaga.findByEmpresa", this.clazz).getResultList();
    }

    public List<Vaga> buscarPorCargo(String cargo) {
        // TODO usar paginacao
        return getEntityManager().createNamedQuery("Vaga.findByCargo", this.clazz).getResultList();
    }
}
