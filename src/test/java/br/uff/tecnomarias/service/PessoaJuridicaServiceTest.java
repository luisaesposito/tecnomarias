package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.entity.Avaliacao;
import br.uff.tecnomarias.domain.entity.PessoaFisica;
import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.enums.PorteEmpresa;
import br.uff.tecnomarias.domain.repository.AvaliacaoRepository;
import br.uff.tecnomarias.domain.repository.PessoaFisicaRepository;
import br.uff.tecnomarias.domain.repository.PessoaJuridicaRepository;
import br.uff.tecnomarias.service.exception.EntidadeNaoEncontradaException;
import br.uff.tecnomarias.service.exception.PessoaInvalidaException;
import br.uff.tecnomarias.service.exception.PessoaJuridicaInvalidaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PessoaJuridicaServiceTest {

    @Mock
    private PessoaFisicaRepository pfRepositoryMock;
    @Mock
    private PessoaJuridicaRepository pjRepositoryMock;
    @Mock
    private AvaliacaoRepository avaliacaoRepositoryMock;
    @InjectMocks
    private PessoaJuridicaService pjService;


    @ParameterizedTest
    @CsvSource({", Nome é obrigatório", " '', Nome é obrigatório"})
    void deveLancarErroSalvarPJNomeInvalido(String nome, String erro) {
        PessoaJuridica pjAlterada = montarPJ();
        pjAlterada.setNome(nome);
        Exception ex = Assertions.assertThrows(PessoaInvalidaException.class, () -> {
            pjService.salvar(pjAlterada);
        });
        Assertions.assertEquals(erro, ex.getMessage());
    }

    @ParameterizedTest
    @CsvSource({", Email é obrigatório", " '', Email é obrigatório"})
    void deveLancarErroSalvarPJEmailInvalido(String email, String erro) {
        PessoaJuridica pjAlterada = montarPJ();
        pjAlterada.setEmail(email);
        Exception ex = Assertions.assertThrows(PessoaInvalidaException.class, () -> {
            pjService.salvar(pjAlterada);
        });
        Assertions.assertEquals(erro, ex.getMessage());
    }

    @Test
    void deveLancarErroSalvarPJTipoPessoaVazio() {
        PessoaJuridica pjAlterada = montarPJ();
        pjAlterada.setTipoPessoa(null);
        Exception ex = Assertions.assertThrows(PessoaInvalidaException.class, () -> {
            pjService.salvar(pjAlterada);
        });
        Assertions.assertEquals("TipoPessoa é obrigatório", ex.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"12345, CNPJ deve ter 14 caracteres", " , CNPJ é obrigatório", " '', CNPJ é obrigatório"})
    void deveLancarErroSalvarPJCPNJInvalido(String cnpj, String erro) {
        PessoaJuridica pjAlterada = montarPJ();
        pjAlterada.setCnpj(cnpj);
        Exception ex = Assertions.assertThrows(PessoaJuridicaInvalidaException.class, () -> {
            pjService.salvar(pjAlterada);
        });
        Assertions.assertEquals(erro, ex.getMessage());
    }

    @Test
    void deveLancarErroSalvarPJPorteEmpresaVazio() {
        PessoaJuridica pjAlterada = montarPJ();
        pjAlterada.setPorteEmpresa(null);
        Exception ex = Assertions.assertThrows(PessoaJuridicaInvalidaException.class, () -> {
            pjService.salvar(pjAlterada);
        });
        Assertions.assertEquals("PorteEmpresa é obrigatório", ex.getMessage());
    }

    @ParameterizedTest
    @CsvSource({" , AreaAtuacao é obrigatório", " '', AreaAtuacao é obrigatório"})
    void deveLancarErroSalvarPJAreaAtuacaoInvalido(String area, String erro) {
        PessoaJuridica pjAlterada = montarPJ();
        pjAlterada.setAreaAtuacao(area);
        Exception ex = Assertions.assertThrows(PessoaJuridicaInvalidaException.class, () -> {
            pjService.salvar(pjAlterada);
        });
        Assertions.assertEquals(erro, ex.getMessage());
    }

    @ParameterizedTest
    @CsvSource({" , Nome é obrigatório", " '', Nome é obrigatório"})
    void deveLancarErroAlterarPJNomeInvalido(String nome, String erro) {
        PessoaJuridica pjAlterada = montarPJ();
        pjAlterada.setNome(nome);
        Exception ex = Assertions.assertThrows(PessoaInvalidaException.class, () -> {
            pjService.alterar(1L, pjAlterada);
        });
        Assertions.assertEquals(erro, ex.getMessage());
    }

    @ParameterizedTest
    @CsvSource({", Email é obrigatório", " '', Email é obrigatório"})
    void deveLancarErroAlterarPJEmailInvalido(String email, String erro) {
        PessoaJuridica pjAlterada = montarPJ();
        pjAlterada.setEmail(email);
        Exception ex = Assertions.assertThrows(PessoaInvalidaException.class, () -> {
            pjService.alterar(1L,pjAlterada);
        });
        Assertions.assertEquals(erro, ex.getMessage());
    }

    @Test
    void deveLancarErroAlterarPJTipoPessoaVazio() {
        PessoaJuridica pjAlterada = montarPJ();
        pjAlterada.setTipoPessoa(null);
        Exception ex = Assertions.assertThrows(PessoaInvalidaException.class, () -> {
            pjService.alterar(1L, pjAlterada);
        });
        Assertions.assertEquals("TipoPessoa é obrigatório", ex.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"12345, CNPJ deve ter 14 caracteres", " , CNPJ é obrigatório", " '', CNPJ é obrigatório"})
    void deveLancarErroAlterarPJCPNJInvalido(String cnpj, String erro) {
        PessoaJuridica pjAlterada = montarPJ();
        pjAlterada.setCnpj(cnpj);
        Exception ex = Assertions.assertThrows(PessoaJuridicaInvalidaException.class, () -> {
            pjService.alterar(1L,pjAlterada);
        });
        Assertions.assertEquals(erro, ex.getMessage());
    }

    @Test
    void deveLancarErroAlterarPJPorteEmpresaVazio() {
        PessoaJuridica pjAlterada = montarPJ();
        pjAlterada.setPorteEmpresa(null);
        Exception ex = Assertions.assertThrows(PessoaJuridicaInvalidaException.class, () -> {
            pjService.alterar(1L, pjAlterada);
        });
        Assertions.assertEquals("PorteEmpresa é obrigatório", ex.getMessage());
    }

    @ParameterizedTest
    @CsvSource({" , AreaAtuacao é obrigatório", " '', AreaAtuacao é obrigatório"})
    void deveLancarErroAlterarPJAreaAtuacaoInvalido(String area, String erro) {
        PessoaJuridica pjAlterada = montarPJ();
        pjAlterada.setAreaAtuacao(area);
        Exception ex = Assertions.assertThrows(PessoaJuridicaInvalidaException.class, () -> {
            pjService.alterar(1L, pjAlterada);
        });
        Assertions.assertEquals(erro, ex.getMessage());
    }

    @Test
    void deveLancarErroAlterarPJInexistente() {
        PessoaJuridica pjAlterada = montarPJ();
        Mockito.when(pjRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception ex = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            pjService.alterar(1L, pjAlterada);
        });
        Assertions.assertEquals("Empresa nao encontrada", ex.getMessage());
    }

    @Test
    void deveLancarErroBuscarPJInexistente() {
        Mockito.when(pjRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception ex = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            pjService.buscarPorId(1L);
        });
        Assertions.assertEquals("Empresa não encontrada", ex.getMessage());
    }


    @Test
    void deveLancarErroRemoverPJInexistente() {
        Mockito.when(pjRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception ex = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            pjService.remover(1L);
        });
        Assertions.assertEquals("Empresa nao encontrada", ex.getMessage());
    }

    @Test
    void deveLancarErroAvaliarPJInexistente() {
        Avaliacao avaliacao = montarAvaliacao();
        Mockito.when(pjRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception ex = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            pjService.avaliarEmpresa(1L, avaliacao);
        });
        Assertions.assertEquals("Empresa não encontrada", ex.getMessage());
    }

    @Test
    void deveLancarErroRemoverAvaliacaoInexistente() {
        Mockito.when(avaliacaoRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception ex = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            pjService.removerAvaliacao(1L);
        });
        Assertions.assertEquals("Avaliacao nao encontrada", ex.getMessage());
    }

    @Test
    void deveRetornarTodosPJ() {
        List<PessoaJuridica> pj = new ArrayList<PessoaJuridica>();
        pj.add(montarPJ());
        Mockito.when(pjRepositoryMock.findAll()).thenReturn(pj);
        List<PessoaJuridica> busca = pjService.buscarTodas();
        Assertions.assertIterableEquals(pj, busca);
    }

    @Test
    void deveRetornarPJ() {
        PessoaJuridica pj = montarPJ();
        Mockito.when(pjRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(pj));
        PessoaJuridica busca = pjService.buscarPorId(1L);
        Assertions.assertSame(pj, busca);
    }

    @Test
    void deveRemoverPJ() {
        PessoaJuridica pj = montarPJ();
        Mockito.when(pjRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(pj));
        pjService.remover(1L);
        Mockito.verify(pjRepositoryMock).delete(pj);
    }

    @Test
    void deveRemoverAvaliacao() {
        Avaliacao av = montarAvaliacao();
        Mockito.when(avaliacaoRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(av));
        pjService.removerAvaliacao(1L);
        Mockito.verify(avaliacaoRepositoryMock).delete(av);
    }

    @Test
    void deveRetornarPJAlterada() {
        PessoaJuridica pj = montarPJ();
        Mockito.when(pjRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(pj));
        pjService.alterar(1L, pj);
        Mockito.verify(pjRepositoryMock).save(pj);
    }

    @Test
    void deveRetornarPJAvaliado() {
        PessoaJuridica pj = montarPJ();
        PessoaFisica avaliadora = montarPF();
        List<Avaliacao> avs = new ArrayList<Avaliacao>();
        pj.setAvaliacoes(avs);
        Avaliacao av = montarAvaliacao();
        Mockito.when(pjRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(pj));
        Mockito.when(pfRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(avaliadora));
        pjService.avaliarEmpresa(1L, av);
        Mockito.verify(pjRepositoryMock).save(pj);
    }


    private static PessoaJuridica montarPJ() {
        PessoaJuridica pj = new PessoaJuridica();
        pj.setNome("Empresa");
        pj.setEmail("empresa@email.com");
        pj.setCnpj("59799043000121");
        pj.setPorteEmpresa(PorteEmpresa.MICROEMPRESA);
        pj.setAreaAtuacao("area testes");
        return pj;
    }

    private static Avaliacao montarAvaliacao() {
        PessoaFisica avaliadora = montarPF();
        Avaliacao av = new Avaliacao();
        av.setId(1L);
        av.setData(LocalDateTime.now());
        av.setComentario("Empresa muito jóia!!");
        av.setAvaliadora(avaliadora);
        av.setNota(5);
        return av;
    }

    private static PessoaFisica montarPF() {
        PessoaFisica pf = new PessoaFisica();
        pf.setId(20L);
        pf.setDataCadastro(LocalDate.now().minusMonths(5));
        return pf;
    }
}
