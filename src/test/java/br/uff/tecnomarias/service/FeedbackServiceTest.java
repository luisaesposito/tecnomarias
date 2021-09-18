package br.uff.tecnomarias.service;

import br.uff.tecnomarias.domain.entity.*;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FeedbackServiceTest {

    @Mock
    private PessoaFisicaRepository pfRepo;

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackService feedbackService;


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
        PessoaFisica pessoaFisica = montarPF();
        pessoaFisica.setFeedback(montarFeedback());
        Mockito.when(pfRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(pessoaFisica));
        Exception ex = Assertions.assertThrows(BadRequestException.class, () -> {
            feedbackService.salvarFeedback(1L, new Feedback());
        });
        Assertions.assertEquals("Usuaria ja avaliou o site", ex.getMessage());
    }

    @Test
    void deveRetornarFeedbackCriado() {
        PessoaFisica pessoaFisica = montarPF();
        Feedback feed = montarFeedback();
        feed.setPessoa(pessoaFisica);
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
        Feedback feed = montarFeedback();
        PessoaFisica pf = montarPF();
        feed.setPessoa(pf);
        Mockito.when(feedbackRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(feed));
        feedbackService.remover(1L);
        Mockito.verify(pfRepo).save(pf);
        Mockito.verify(feedbackRepository).delete(feed);
    }

    @Test
    void deveRetornarFeedbacksRecentes(){
        List<Feedback> fb = new ArrayList<Feedback>();
        fb.add(montarFeedback());
        fb.add(montarFeedback());
        fb.add(montarFeedback());
        Mockito.when(feedbackRepository.findTop3ByOrderByIdDesc()).thenReturn(fb);
        List<Feedback> busca = feedbackService.buscarRecentes();
        Assertions.assertIterableEquals(fb, busca);
    }

    private static Feedback montarFeedback() {
        Feedback fb = new Feedback();
        fb.setComentario("Empresa muito boa!");
        return fb;
    }

    private static PessoaFisica montarPF() {
        PessoaFisica pf = new PessoaFisica();
        pf.setNome("Renan Henrique");
        pf.setEmail("renan@email.com");
        return pf;
    }
}
