package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.Feedback;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeedbackDTO {

    public Long id;
    public String comentario;
    public Long idAvaliadora;

    public FeedbackDTO() {
    }

    public FeedbackDTO(Feedback feedback) {
        this.id = feedback.getId();
        this.comentario = feedback.getComentario();
        this.idAvaliadora = feedback.getPessoa().getId();
    }

    public Feedback toEntity() {
        Feedback feedback = new Feedback(this.comentario);
        return feedback;
    }

    public static List<FeedbackDTO> toDTOList(List<Feedback> feedbacks) {
        return feedbacks.stream().map(FeedbackDTO::new).collect(Collectors.toList());
    }
}
