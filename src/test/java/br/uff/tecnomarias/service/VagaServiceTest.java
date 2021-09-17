package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.entity.Avaliacao;
import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.entity.Vaga;
import br.uff.tecnomarias.domain.enums.Cargo;
import br.uff.tecnomarias.domain.enums.PorteEmpresa;
import br.uff.tecnomarias.domain.repository.AvaliacaoRepository;
import br.uff.tecnomarias.domain.repository.PessoaJuridicaRepository;
import br.uff.tecnomarias.domain.repository.VagaRepository;
import br.uff.tecnomarias.service.exception.BadRequestException;
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
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;


@ExtendWith(MockitoExtension.class)
public class VagaServiceTest {

    @Mock
    private PessoaJuridicaRepository pjRepositoryMock;
    @Mock
    private VagaRepository vgRepositoryMock;
    @InjectMocks
    private VagaService vgService;

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
    void deveRetornarErroVagaSemAreaAtuacao(){
        Vaga vg = montarVaga();
        vg.setAreaAtuacao(null);
        Set<ConstraintViolation<Vaga>> violations = validator.validate(vg);
        Assertions.assertTrue(violations.size() == 1);
    }

    @Test
    void deveRetornarErroVagaSemDescricao(){
        Vaga vg = montarVaga();
        vg.setDescricao(null);
        Set<ConstraintViolation<Vaga>> violations = validator.validate(vg);
        Assertions.assertTrue(violations.size() == 1);
    }

    @Test
    void deveRetornarErroVagaSemCargo(){
        Vaga vg = montarVaga();
        vg.setCargo(null);
        Set<ConstraintViolation<Vaga>> violations = validator.validate(vg);
        Assertions.assertTrue(violations.size() == 1);
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

    private static Vaga montarVaga() {
        Vaga vg = new Vaga();
        vg.setAreaAtuacao("TI");
        Cargo cargo =  Cargo.GERENTE;
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
