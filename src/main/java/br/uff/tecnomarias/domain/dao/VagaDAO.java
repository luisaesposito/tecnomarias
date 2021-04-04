package br.uff.tecnomarias.domain.dao;

import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.entity.Vaga;

import javax.inject.Inject;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.stream.Collectors;

public class VagaDAO extends BaseDAOImpl<Vaga> {

    @Inject
    public VagaDAO() {
        this.clazz = Vaga.class;
    }

    public List<Vaga> buscarPorEmpresa(PessoaJuridica empresa) {
        EntityTransaction tx = getTransaction();
        List<Vaga> resultList;
        try {
            tx.begin();
            resultList = getEntityManager().createNamedQuery("Vaga.findByEmpresa", this.clazz)
                    .setParameter("empresa", empresa)
                    .getResultList();
            tx.commit();
        } catch (RuntimeException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            closeEM();
        }
        return resultList;
    }

    public List<Vaga> buscarPorAreaAtuacao(String areaAtuacao) {
        // TODO usar paginacao
        EntityTransaction tx = getTransaction();
        List<Vaga> resultList;
        try {
            tx.begin();
            resultList = getEntityManager().createNamedQuery("Vaga.findByArea", this.clazz)
                    .setParameter("area", areaAtuacao)
                    .getResultList();
            tx.commit();
        } catch (RuntimeException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            closeEM();
        }
        return resultList;
    }

    public List<Vaga> buscarPorCargo(String cargo) {
        // TODO usar paginacao
        EntityTransaction tx = getTransaction();
        List<Vaga> resultList;
        try {
            tx.begin();
            resultList = getEntityManager().createNamedQuery("Vaga.findByCargo", this.clazz)
                    .setParameter("cargo", cargo)
                    .getResultList();
            tx.commit();
        } catch (RuntimeException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            closeEM();
        }
        return resultList;
    }

    public List<Vaga> buscarPorLocalidade(String localidade) {
        // TODO usar paginacao
        EntityTransaction tx = getTransaction();
        List<Vaga> resultList;
        try {
            tx.begin();
            resultList = getEntityManager().createNamedQuery("Vaga.findByLocalidade", this.clazz)
                    .setParameter("localidade", localidade)
                    .getResultList();
            tx.commit();
        } catch (RuntimeException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            closeEM();
        }
        return resultList;
    }

    public List<String> listarAreaAtuacao() {
        List<String> result = getEntityManager().createQuery("SELECT v.areaAtuacao FROM Vaga v").getResultList();
        return result.stream().distinct().collect(Collectors.toList());
    }
}
