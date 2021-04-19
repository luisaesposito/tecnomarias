package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.Feedback;
import br.uff.tecnomarias.domain.entity.Vaga;

import java.util.List;
import java.util.stream.Collectors;

public class FeedbackDTO {

    public Long id;
    public String comentario;
    public Long idPessoa;

    public FeedbackDTO() { }

    public FeedbackDTO(Feedback feedback) {
        this.id = feedback.getId();
        this.comentario = feedback.getComentario();
        this.idPessoa = feedback.getPessoa().getId();
    }

    public Feedback toEntity() {
        Feedback feedback = new Feedback(this.comentario);
        return feedback;
    }

    public static List<FeedbackDTO> toDTOList(List<Feedback> vagas) {
        return vagas.stream().map(FeedbackDTO::new).collect(Collectors.toList());
    }
}
