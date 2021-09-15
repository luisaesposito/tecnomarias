package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.entity.Feedback;
import br.uff.tecnomarias.domain.entity.Pessoa;
import br.uff.tecnomarias.domain.entity.PessoaFisica;
import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.enums.PorteEmpresa;
import br.uff.tecnomarias.domain.repository.FeedbackRepository;
import br.uff.tecnomarias.domain.repository.PessoaFisicaRepository;
import br.uff.tecnomarias.rest.dto.FeedbackDTO;
import br.uff.tecnomarias.service.exception.BadRequestException;
import br.uff.tecnomarias.service.exception.EntidadeNaoEncontradaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FeedbackServiceTest {

    @Mock
    PessoaFisicaRepository pfRepo;

    @Mock
    FeedbackRepository feedbackRepository;

    @InjectMocks
    FeedbackService feedbackService;

    @Test
    void deveRetornar3Ultimos() {
        List<Feedback> search = feedbackService.buscarRecentes();
        Assertions.assertEquals(3, search.size());
    }

    @Test
    void deveRetornarErroUsuariaInexistente() {
        Mockito.when(pfRepo.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception ex = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            feedbackService.salvarFeedback(1L, new Feedback());
        });
        Assertions.assertEquals("Pessoa não encontrada", ex.getMessage());
    }

    @Test
    void deveRetornarErroFeedbackExistente() {
        PessoaFisica pessoaFisica = montarPF(true);
        Mockito.when(pfRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(pessoaFisica));
        Exception ex = Assertions.assertThrows(BadRequestException.class, () -> {
            feedbackService.salvarFeedback(1L, new Feedback());
        });
        Assertions.assertEquals("Usuaria ja avaliou o site", ex.getMessage());
    }

    @Test
    void deveRetornarFeedback() {
        PessoaFisica pessoaFisica = montarPF(false);
        Feedback feed = montarFeedback(true);
        Mockito.when(pfRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(pessoaFisica));
        Feedback save = feedbackService.salvarFeedback(1L, feed);
        Assertions.assertEquals(feed, save);
    }

    @Test
    void deveRetornarErroFeedbackInexistente() {
        Mockito.when(feedbackRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception ex = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            feedbackService.remover(1L);
        });
        Assertions.assertEquals("Feedback nao encontrado", ex.getMessage());
    }

    @Test
    void deveRetornarSucessoFeedbackRemovido() {
        Feedback feed = montarFeedback(true);
        Mockito.when(feedbackRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(feed));
        boolean remove =  feedbackService.remover(1L);
        Assertions.assertEquals(remove, true);
    }

    private static Feedback montarFeedback(boolean pessoa) {
        Feedback fb = new Feedback();
        fb.setComentario("Empresa muito boa!");
        if (pessoa) fb.setPessoa(montarPF(false));
        return fb;
    }

    private static PessoaFisica montarPF(boolean feedback) {
        PessoaFisica pf = new PessoaFisica();
        pf.setNome("Renan Henrique");
        pf.setEmail("renan@email.com");
        if (feedback) pf.setFeedback(montarFeedback(false));
        return pf;
    }
}
