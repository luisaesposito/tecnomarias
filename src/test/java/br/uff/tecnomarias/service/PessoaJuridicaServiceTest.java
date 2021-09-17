package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.entity.Avaliacao;
import br.uff.tecnomarias.domain.entity.Pessoa;
import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.enums.PorteEmpresa;
import br.uff.tecnomarias.domain.repository.AvaliacaoRepository;
import br.uff.tecnomarias.domain.repository.PessoaJuridicaRepository;
import br.uff.tecnomarias.service.PessoaJuridicaService;
import br.uff.tecnomarias.service.exception.EntidadeNaoEncontradaException;
import org.junit.AfterClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@ExtendWith(MockitoExtension.class)
public class PessoaJuridicaServiceTest {

    @Mock
    private PessoaJuridicaRepository pjRepositoryMock;
    @Mock
    private AvaliacaoRepository avaliacaoRepositoryMock;
    @InjectMocks
    private PessoaJuridicaService pjService;

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterClass
    public static void close() {
        factory.close();
    }

    @Test
    void deveRetornarErroPJInvalidoComCNPJInvalido(){
        PessoaJuridica pjAlterada = montarPJ();
        pjAlterada.setCnpj("59799043");
        Set<ConstraintViolation<PessoaJuridica>> violations = validator.validate(pjAlterada);
        Assertions.assertTrue(violations.size() == 1);
    }

    @Test
    void deveRetornarErroPJInvalidoSemCNPJ(){
        PessoaJuridica pjAlterada = montarPJ();
        pjAlterada.setCnpj(null);
        Set<ConstraintViolation<PessoaJuridica>> violations = validator.validate(pjAlterada);
        Assertions.assertTrue(violations.size() == 1);
    }

    @Test
    void deveRetornarErroPJInvalidoSemPorteEmpresa(){
        PessoaJuridica pjAlterada = montarPJ();
        pjAlterada.setPorteEmpresa(null);
        Set<ConstraintViolation<PessoaJuridica>> violations = validator.validate(pjAlterada);
        Assertions.assertTrue(violations.size() == 1);
    }

    @Test
    void deveRetornarErroPJInvalidoSemAreaAtuacao(){
        PessoaJuridica pjAlterada = montarPJ();
        pjAlterada.setAreaAtuacao(null);
        Set<ConstraintViolation<PessoaJuridica>> violations = validator.validate(pjAlterada);
        Assertions.assertTrue(violations.size() == 1);
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

//    @Test
//    void deveRetornarPJAvaliado() {
//        Avaliacao avaliacao = montarAvaliacao();
//        PessoaJuridica pj = montarPJ();
//        PessoaJuridica pjAlterada = montarPJ();
//        Avaliacao avaliacaoAlterada = montarAvaliacao();
//        avaliacaoAlterada.setEmpresa(pj);
//        pjAlterada.setAvaliacoes(List.of(avaliacao));
//        Mockito.when(pjRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(pj));
//        Mockito.when(avaliacaoRepositoryMock.findTopByOrderByDataDesc()).thenReturn(avaliacaoAlterada);
//        Mockito.when(pjRepositoryMock.save(pj)).thenReturn(pjAlterada);
//        Avaliacao save = pjService.avaliarEmpresa(1L, avaliacao);
//        Assertions.assertEquals(avaliacao, save);
//    }

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
        Avaliacao av = new Avaliacao();
        av.setId(1L);
        av.setData(LocalDateTime.now());
        av.setComentario("Empresa muito jóia!!");
        av.setNomeAvaliadora("Ester Matos");
        av.setNota(5);
        return av;
    }
}
