package br.uff.tecnomarias.domain.dao;


import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.entity.Vaga;

import javax.inject.Inject;
import java.util.List;

public class VagaDAO extends BaseDAOImpl<Vaga> {

    @Inject
    public VagaDAO(Class<Vaga> clazz) {
        super(clazz);
    }

    public List<Vaga> buscarPorEmpresa(PessoaJuridica empresa) {
        return getEntityManager().createNamedQuery("Vaga.findByEmpresa", this.clazz).getResultList();
    }


    public List<Vaga> buscarPorCargo(String cargo) {
        // TODO paginacao?
        // ou paginacao é para os fracos, usar pagina com 500 itens mesmo
        return getEntityManager().createNamedQuery("Vaga.findByCargo", this.clazz).getResultList();
    }
}
