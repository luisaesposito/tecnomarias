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

    public int count() {
        return (int) getEntityManager().createNamedQuery("Vaga.countAll").getSingleResult();
    }

    public List<Vaga> buscarPorEmpresa(PessoaJuridica empresa) {
        return getEntityManager().createNamedQuery("Vaga.findByEmpresa", this.clazz).getResultList();
    }

    public List<Vaga> buscarPorAreaAtuacao(String areaAtuacao) {
        // TODO usar paginacao
        return getEntityManager().createNamedQuery("Vaga.findByArea", this.clazz).getResultList();
    }

    public List<Vaga> buscarPorCargo(String cargo) {
        // TODO usar paginacao
        return getEntityManager().createNamedQuery("Vaga.findByCargo", this.clazz).getResultList();
    }

    public List<Vaga> buscarPorLocalidade(String localidade) {
        // TODO usar paginacao
        return getEntityManager().createNamedQuery("Vaga.findByLocalidade", this.clazz).getResultList();
    }

}
