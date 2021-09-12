package br.uff.tecnomarias.rest.resource;

import br.uff.tecnomarias.domain.entity.Pessoa;
import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.enums.PorteEmpresa;
import br.uff.tecnomarias.domain.repository.AvaliacaoRepository;
import br.uff.tecnomarias.domain.repository.PessoaJuridicaRepository;
import br.uff.tecnomarias.service.PessoaJuridicaService;
import br.uff.tecnomarias.service.exception.EntidadeNaoEncontradaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PessoaJuridicaResourceTest {

    @Mock
    private PessoaJuridicaRepository pjRepositoryMock;
    @Mock
    private AvaliacaoRepository avaliacaoRepositoryMock;
    @InjectMocks
    private PessoaJuridicaService pjService;

    @Test
    void deveLancarErroAlterarPJInexistente() {
        PessoaJuridica pjAlterada = montarPJ();
        Mockito.when(pjRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Exception ex = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            pjService.alterar(1L, pjAlterada);
        });

        Assertions.assertEquals("Empresa nao encontrada", ex.getMessage());
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
}
