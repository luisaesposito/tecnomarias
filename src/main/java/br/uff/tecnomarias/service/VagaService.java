package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.dao.PessoaJuridicaDAO;
import br.uff.tecnomarias.domain.dao.VagaDAO;
import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.entity.Vaga;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.WebApplicationException;
import java.util.List;

@ManagedBean
public class VagaService {

    @Inject
    VagaDAO vagaDAO;

    @Inject
    PessoaJuridicaDAO pjDAO;

    public Vaga salvar(@Valid Vaga vaga) {
        vagaDAO.salvar(vaga);
        return vaga;
    }

    public Vaga buscarPorId(final Long id) {
        return vagaDAO.buscarPorId(id);
    }

    public int count() {
        return vagaDAO.count();
    }

    public List<Vaga> buscarTodas(int start, int size) {
        return vagaDAO.buscarTodas();
    }

    public List<Vaga> buscarPorEmpresa(final Long idEmpresa) {
        PessoaJuridica empresa = pjDAO.buscarPorIdOptional(idEmpresa)
                .orElseThrow(() -> new WebApplicationException("Empresa nao encontrada", 400));
        return vagaDAO.buscarPorEmpresa(empresa);
    }

    public List<Vaga> buscarPorAreaAtuacao(String areaAtuacao) {
        return vagaDAO.buscarPorAreaAtuacao(areaAtuacao);
    }

    public List<Vaga> buscarPorCargo(String cargo) {
        return vagaDAO.buscarPorCargo(cargo);
    }

    public List<Vaga> buscarPorLocalidade(String localidade) {
        return vagaDAO.buscarPorLocalidade(localidade);
    }

    public Vaga alterar(final Long id, @Valid final Vaga vagaAlterada) {
        Vaga vagaSalva = vagaDAO.buscarPorIdOptional(id)
                .orElseThrow(() -> new WebApplicationException("Vaga nao encontrada", 404));
        return vagaSalva.atualizarDados(vagaAlterada);
    }

    public void remover(final Long id) {
        Vaga vaga = vagaDAO.buscarPorIdOptional(id)
                .orElseThrow(() -> new WebApplicationException("Vaga nao encontrada", 404));
        vagaDAO.remover(vaga);
    }

}
