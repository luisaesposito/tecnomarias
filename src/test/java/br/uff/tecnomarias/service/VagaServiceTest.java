package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.entity.Vaga;
import br.uff.tecnomarias.domain.enums.Cargo;
import br.uff.tecnomarias.domain.enums.PorteEmpresa;
import br.uff.tecnomarias.domain.repository.PessoaJuridicaRepository;
import br.uff.tecnomarias.domain.repository.VagaRepository;
import br.uff.tecnomarias.service.exception.BadRequestException;
import br.uff.tecnomarias.service.exception.EntidadeNaoEncontradaException;
import br.uff.tecnomarias.service.exception.VagaInvalidaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class VagaServiceTest {

    @Mock
    private PessoaJuridicaRepository pjRepositoryMock;
    @Mock
    private VagaRepository vgRepositoryMock;
    @InjectMocks
    private VagaService vgService;

    @Test
    void deveLancarErroSalvarVagaDescricaoVazio() {
        Vaga vaga = montarVaga();
        vaga.setDescricao(null);
        Exception ex = Assertions.assertThrows(VagaInvalidaException.class, () -> {
            vgService.salvar(vaga);
        });
        Assertions.assertEquals("Descrição é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarErroSalvarVagaDescricaoEmBranco() {
        Vaga vaga = montarVaga();
        vaga.setDescricao("");
        Exception ex = Assertions.assertThrows(VagaInvalidaException.class, () -> {
            vgService.salvar(vaga);
        });
        Assertions.assertEquals("Descrição é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarErroSalvarVagaCargoVazio() {
        Vaga vaga = montarVaga();
        vaga.setCargo(null);
        Exception ex = Assertions.assertThrows(VagaInvalidaException.class, () -> {
            vgService.salvar(vaga);
        });
        Assertions.assertEquals("Cargo é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarErroSalvarVagaAreaAtuacaoVazio() {
        Vaga vaga = montarVaga();
        vaga.setAreaAtuacao(null);
        Exception ex = Assertions.assertThrows(VagaInvalidaException.class, () -> {
            vgService.salvar(vaga);
        });
        Assertions.assertEquals("Área Atuação é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarErroSalvarVagaAreaAtuacaoEmBranco() {
        Vaga vaga = montarVaga();
        vaga.setAreaAtuacao("");
        Exception ex = Assertions.assertThrows(VagaInvalidaException.class, () -> {
            vgService.salvar(vaga);
        });
        Assertions.assertEquals("Área Atuação é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarErroAlterarVagaDescricaoVazio() {
        Vaga vaga = montarVaga();
        vaga.setDescricao(null);
        Exception ex = Assertions.assertThrows(VagaInvalidaException.class, () -> {
            vgService.alterar(1L, vaga);
        });
        Assertions.assertEquals("Descrição é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarErroAlterarVagaDescricaoEmBranco() {
        Vaga vaga = montarVaga();
        vaga.setDescricao("");
        Exception ex = Assertions.assertThrows(VagaInvalidaException.class, () -> {
            vgService.alterar(1L, vaga);
        });
        Assertions.assertEquals("Descrição é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarErroAlterarVagaCargoVazio() {
        Vaga vaga = montarVaga();
        vaga.setCargo(null);
        Exception ex = Assertions.assertThrows(VagaInvalidaException.class, () -> {
            vgService.alterar(1L, vaga);
        });
        Assertions.assertEquals("Cargo é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarErroAlterarVagaAreaAtuacaoVazio() {
        Vaga vaga = montarVaga();
        vaga.setAreaAtuacao(null);
        Exception ex = Assertions.assertThrows(VagaInvalidaException.class, () -> {
            vgService.alterar(1L, vaga);
        });
        Assertions.assertEquals("Área Atuação é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarErroAlterarVagaAreaAtuacaoEmBranco() {
        Vaga vaga = montarVaga();
        vaga.setAreaAtuacao("");
        Exception ex = Assertions.assertThrows(VagaInvalidaException.class, () -> {
            vgService.alterar(1L, vaga);
        });
        Assertions.assertEquals("Área Atuação é obrigatório", ex.getMessage());
    }


    @Test
    void deveLancarErroSalvarVagaComEmpresaInexistente() {
        Vaga vg = montarVaga();
        vg.setEmpresa(montarPJ());
        Mockito.when(pjRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception ex = Assertions.assertThrows(BadRequestException.class, () -> {
            vgService.salvar(vg);
        });
        Assertions.assertEquals("Empresa nao encontrada", ex.getMessage());
    }

    @Test
    void deveLancarErroBuscarVagaInexistente() {
        Mockito.when(vgRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception ex = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            vgService.buscarPorId(1L);
        });
        Assertions.assertEquals("Vaga nao encontrada", ex.getMessage());
    }

    @Test
    void deveLancarErroBuscarVagaPorEmpresaComEmpresaInexistente() {
        Mockito.when(pjRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception ex = Assertions.assertThrows(BadRequestException.class, () -> {
            vgService.buscarPorEmpresa(1L);
        });
        Assertions.assertEquals("Empresa nao encontrada", ex.getMessage());
    }

    @Test
    void deveRetornarVagaPorEmpresa() {
        PessoaJuridica pj = montarPJ();
        List<Vaga> vg = new ArrayList<Vaga>();
        vg.add(montarVaga());
        Mockito.when(pjRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(pj));
        Mockito.when(vgRepositoryMock.findByEmpresa(pj)).thenReturn(vg);
        List<Vaga> busca = vgService.buscarPorEmpresa(1L);
        Assertions.assertEquals(busca, vg);
    }

    @Test
    void deveLancarErroAlterarVagaInexistente() {
        Vaga vgAlterada = montarVaga();
        Mockito.when(vgRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception ex = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            vgService.alterar(1L, vgAlterada);
        });
        Assertions.assertEquals("Vaga nao encontrada", ex.getMessage());
    }

    @Test
    void deveLancarErroRemoverVagaInexistente() {
        Mockito.when(vgRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception ex = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            vgService.remover(1L);
        });
        Assertions.assertEquals("Vaga nao encontrada", ex.getMessage());
    }

    @Test
    void deveRetornarVaga() {
        Vaga vg = montarVaga();
        Mockito.when(vgRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(vg));
        Vaga busca = vgService.buscarPorId(1L);
        Assertions.assertSame(vg, busca);
    }

    @Test
    void deveRetornarCountVaga() {
        Mockito.when(vgRepositoryMock.count()).thenReturn(3L);
        Long busca = vgService.count();
        Assertions.assertSame(3L, busca);
    }

    @Test
    void deveRetornarTodasVagas(){
        List<Vaga> vg = new ArrayList<Vaga>();
        vg.add(montarVaga());
        Mockito.when(vgRepositoryMock.findAll()).thenReturn(vg);
        List<Vaga> busca = vgService.buscarTodas();
        Assertions.assertIterableEquals(vg, busca);
    }

    @Test
    void deveRetornarVagaPorAreaAtuacao() {
        List<Vaga> vg = new ArrayList<Vaga>();
        vg.add(montarVaga());
        Mockito.when(vgRepositoryMock.findByAreaAtuacaoContainingIgnoreCase(Mockito.anyString())).thenReturn(vg);
        List<Vaga> busca = vgService.buscarPorAreaAtuacao("TI");
        Assertions.assertSame(vg, busca);
    }

    @Test
    void deveRetornarVagaPorCargo() {
        List<Vaga> vg = new ArrayList<Vaga>();
        vg.add(montarVaga());
        Mockito.when(vgRepositoryMock.findByCargo(Mockito.any())).thenReturn(vg);
        List<Vaga> busca = vgService.buscarPorCargo(Cargo.GERENTE);
        Assertions.assertSame(vg, busca);
    }

    @Test
    void deveRetornarVagaPorLocalidade() {
        List<Vaga> vg = new ArrayList<Vaga>();
        vg.add(montarVaga());
        Mockito.when(vgRepositoryMock.findByLocalidade(Mockito.anyString())).thenReturn(vg);
        List<Vaga> busca = vgService.buscarPorLocalidade("Rio de Janeiro");
        Assertions.assertSame(vg, busca);
    }

    @Test
    void deveRetornarListaAreaAtuacao() {
        List<String> area = List.of("engenharia", "medicina", "educação");
        Mockito.when(vgRepositoryMock.listAreaAtuacao()).thenReturn(area);
        List<String> busca = vgService.listarAreaAtuacao();
        Assertions.assertIterableEquals(area, busca);
    }

    @Test
    void deveRetornarVagaSalva() {
        Vaga vg = montarVaga();
        PessoaJuridica pj = montarPJ();
        List<Vaga> vgs = new ArrayList<Vaga>();
        pj.setVagas(vgs);
        vg.setEmpresa(pj);
        Mockito.when(pjRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(pj));
        vgService.salvar(vg);
        Mockito.verify(pjRepositoryMock).save(pj);
    }

    @Test
    void deveRetornarVagaAlterada() {
        Vaga vg = montarVaga();
        Mockito.when(vgRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(vg));
        vgService.alterar(1L, vg);
        Mockito.verify(vgRepositoryMock).save(vg);
    }

    @Test
    void deveRemoverVaga() {
        Vaga vg = montarVaga();
        PessoaJuridica pj = montarPJ();
        List<Vaga> vgs = new ArrayList<Vaga>();
        vgs.add(vg);
        pj.setVagas(vgs);
        vg.setEmpresa(pj);
        Mockito.when(vgRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(vg));
        vgService.remover(1L);
        Mockito.verify(vgRepositoryMock).delete(vg);
    }

    private static Vaga montarVaga() {
        Vaga vg = new Vaga();
        vg.setAreaAtuacao("TI");
        Cargo cargo = Cargo.GERENTE;
        vg.setCargo(cargo);
        vg.setLocalidade("Rio de Janeiro");
        vg.setDescricao("Vaga boa para gerente");
        vg.setValor(2000.00);
        return vg;
    }

    private static PessoaJuridica montarPJ() {
        PessoaJuridica pj = new PessoaJuridica();
        pj.setNome("Empresa");
        pj.setId(1L);
        pj.setEmail("empresa@email.com");
        pj.setCnpj("59799043000121");
        pj.setPorteEmpresa(PorteEmpresa.MICROEMPRESA);
        pj.setAreaAtuacao("area testes");
        return pj;
    }
}
