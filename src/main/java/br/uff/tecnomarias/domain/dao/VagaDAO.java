package br.uff.tecnomarias.domain.dao;

import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.entity.Vaga;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class VagaDAO extends BaseDAOImpl<Vaga> {

    @Inject
    public VagaDAO() {
        this.clazz = Vaga.class;
    }

    public List<Vaga> buscarPorEmpresa(PessoaJuridica empresa) {
        return entityManager.createNamedQuery("Vaga.findByEmpresa", this.clazz)
                    .setParameter("empresa", empresa)
                    .getResultList();
    }

    public List<Vaga> buscarPorAreaAtuacao(String areaAtuacao) {
        return entityManager.createNamedQuery("Vaga.findByArea", this.clazz)
                    .setParameter("area", areaAtuacao)
                    .getResultList();
    }

    public List<Vaga> buscarPorCargo(String cargo) {
        // TODO usar paginacao
        return entityManager.createNamedQuery("Vaga.findByCargo", this.clazz)
                    .setParameter("cargo", cargo)
                    .getResultList();
    }

    public List<Vaga> buscarPorLocalidade(String localidade) {
        // TODO usar paginacao
        return entityManager.createNamedQuery("Vaga.findByLocalidade", this.clazz)
                    .setParameter("localidade", localidade)
                    .getResultList();
    }

    public List<String> listarAreaAtuacao() {
        List<String> result = entityManager.createQuery("SELECT v.areaAtuacao FROM Vaga v").getResultList();
        return result.stream().distinct().collect(Collectors.toList());
    }
}
