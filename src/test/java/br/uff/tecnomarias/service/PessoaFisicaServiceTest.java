package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.entity.Avaliacao;
import br.uff.tecnomarias.domain.entity.Feedback;
import br.uff.tecnomarias.domain.entity.PessoaFisica;
import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.enums.TipoPessoa;
import br.uff.tecnomarias.domain.repository.AvaliacaoRepository;
import br.uff.tecnomarias.domain.repository.FeedbackRepository;
import br.uff.tecnomarias.domain.repository.PessoaFisicaRepository;
import br.uff.tecnomarias.service.exception.EntidadeNaoEncontradaException;
import br.uff.tecnomarias.service.exception.PessoaInvalidaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class PessoaFisicaServiceTest {

    @Mock
    private AvaliacaoRepository avRepositoryMock;
    @Mock
    private FeedbackRepository feedbackRepository;
    @Mock
    private PessoaFisicaRepository pfRepositoryMock;
    @InjectMocks
    private PessoaFisicaService pfService;

    @ParameterizedTest
    @CsvSource({", Nome é obrigatório", " '', Nome é obrigatório"})
    void deveLancarErroSalvarPFNomeInvalido(String nome, String erro) {
        PessoaFisica pfAlterada = montarPF();
        pfAlterada.setNome(nome);
        Exception ex = Assertions.assertThrows(PessoaInvalidaException.class, () -> {
            pfService.salvar(pfAlterada);
        });
        Assertions.assertEquals(erro, ex.getMessage());
    }

    @ParameterizedTest
    @CsvSource({", Email é obrigatório", " '', Email é obrigatório"})
    void deveLancarErroSalvarPFEmailInvalido(String email, String erro) {
        PessoaFisica pfAlterada = montarPF();
        pfAlterada.setEmail(email);
        Exception ex = Assertions.assertThrows(PessoaInvalidaException.class, () -> {
            pfService.salvar(pfAlterada);
        });
        Assertions.assertEquals(erro, ex.getMessage());
    }

    @Test
    void deveLancarErroSalvarPFTipoPessoaVazio() {
        PessoaFisica pfAlterada = montarPF();
        pfAlterada.setTipoPessoa(null);
        Exception ex = Assertions.assertThrows(PessoaInvalidaException.class, () -> {
            pfService.salvar(pfAlterada);
        });
        Assertions.assertEquals("TipoPessoa é obrigatório", ex.getMessage());
    }

    @ParameterizedTest
    @CsvSource({", Nome é obrigatório", " '', Nome é obrigatório"})
    void deveLancarErroAlterarPFNomeInvalido(String nome, String erro) {
        PessoaFisica pfAlterada = montarPF();
        pfAlterada.setNome(nome);
        Exception ex = Assertions.assertThrows(PessoaInvalidaException.class, () -> {
            pfService.alterar(1L, pfAlterada);
        });
        Assertions.assertEquals(erro, ex.getMessage());
    }

    @ParameterizedTest
    @CsvSource({", Email é obrigatório", " '', Email é obrigatório"})
    void deveLancarErroAlterarPFEmailInvalido(String email, String erro) {
        PessoaFisica pfAlterada = montarPF();
        pfAlterada.setEmail(email);
        Exception ex = Assertions.assertThrows(PessoaInvalidaException.class, () -> {
            pfService.alterar(1L, pfAlterada);
        });
        Assertions.assertEquals(erro, ex.getMessage());
    }

    @Test
    void deveLancarErroAlterarPFTipoPessoaVazio() {
        PessoaFisica pfAlterada = montarPF();
        pfAlterada.setTipoPessoa(null);
        Exception ex = Assertions.assertThrows(PessoaInvalidaException.class, () -> {
            pfService.alterar(1L, pfAlterada);
        });
        Assertions.assertEquals("TipoPessoa é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarErroAlterarPFInexistente() {
        PessoaFisica pfAlterada = montarPF();
        Mockito.when(pfRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception ex = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            pfService.alterar(1L, pfAlterada);
        });
        Assertions.assertEquals("Pessoa nao encontrada", ex.getMessage());
    }

    @Test
    void deveLancarErroBuscarPFInexistente() {
        Mockito.when(pfRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception ex = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            pfService.buscarPorId(1L);
        });
        Assertions.assertEquals("Pessoa nao encontrada", ex.getMessage());
    }


    @Test
    void deveLancarErroRemoverPFInexistente() {
        Mockito.when(pfRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception ex = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            pfService.remover(1L);
        });
        Assertions.assertEquals("Pessoa nao encontrada", ex.getMessage());
    }

    @Test
    void deveRemoverPFEFeedback() {
        PessoaFisica pf = montarPF();
        Feedback fb = montarFeedback();
        pf.setFeedback(fb);
        Mockito.when(pfRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(pf));
        Mockito.when(avRepositoryMock.findByAvaliadora(Mockito.any(PessoaFisica.class))).thenReturn(Optional.empty());
        pfService.remover(1L);
        Mockito.verify(feedbackRepository).delete(fb);
        Mockito.verify(pfRepositoryMock).delete(pf);
        Mockito.verify(avRepositoryMock, Mockito.times(0)).delete(Mockito.any(Avaliacao.class));
    }

    @Test
    void deveRemoverPFSemFeedback() {
        PessoaFisica pf = montarPF();
        Mockito.when(pfRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(pf));
        Mockito.when(avRepositoryMock.findByAvaliadora(Mockito.any(PessoaFisica.class))).thenReturn(Optional.empty());
        pfService.remover(1L);
        Mockito.verify(pfRepositoryMock).delete(pf);
        Mockito.verify(avRepositoryMock, Mockito.times(0)).delete(Mockito.any(Avaliacao.class));
    }

    @Test
    void deveRemoverPFComAvaliacao() {
        PessoaFisica pf = montarPF();
        Avaliacao avaliacao = montarAvaliacao(pf);
        Mockito.when(pfRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(pf));
        Mockito.when(avRepositoryMock.findByAvaliadora(Mockito.any(PessoaFisica.class))).thenReturn(Optional.of(avaliacao));
        pfService.remover(1L);
        Mockito.verify(pfRepositoryMock).delete(pf);
        Mockito.verify(avRepositoryMock).delete(avaliacao);
    }

    @Test
    void deveRetornarTodosPF() {
        List<PessoaFisica> pf = new ArrayList<PessoaFisica>();
        pf.add(montarPF());
        Mockito.when(pfRepositoryMock.findAll()).thenReturn(pf);
        List<PessoaFisica> busca = pfService.buscarTodas();
        Assertions.assertIterableEquals(pf, busca);
    }

    @Test
    void deveRetornarPF() {
        PessoaFisica pf = montarPF();
        Mockito.when(pfRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(pf));
        PessoaFisica busca = pfService.buscarPorId(1L);
        Assertions.assertSame(pf, busca);
    }

    @Test
    void deveRetornarPFSalva() {
        PessoaFisica pf = montarPF();
        pfService.salvar(pf);
        Mockito.verify(pfRepositoryMock).save(pf);
    }

    @Test
    void deveRetornarPFAlterado() {
        PessoaFisica pf = montarPF();
        Mockito.when(pfRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(pf));
        pfService.alterar(1L, pf);
        Mockito.verify(pfRepositoryMock).save(pf);
    }

    private static Feedback montarFeedback() {
        Feedback fb = new Feedback();
        fb.setComentario("Empresa muito boa!");
        return fb;
    }

    private static PessoaFisica montarPF() {
        PessoaFisica pf = new PessoaFisica();
        pf.setNome("Julinha Maisena");
        pf.setEmail("julinha@email.com");
        pf.setTipoPessoa(TipoPessoa.PF);
        return pf;
    }

    private static Avaliacao montarAvaliacao(PessoaFisica pf) {
        Avaliacao av = new Avaliacao();
        av.setAvaliadora(pf);
        av.setId(8L);
        return av;
    }
}
