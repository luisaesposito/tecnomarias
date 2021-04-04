package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.Feedback;

public class FeedbackDTO {

    private Long id;
    private String comentario;
    private Long idPessoa;

    public FeedbackDTO() {
    }

    public FeedbackDTO(Feedback feedback) {
        this.id = feedback.getId();
        this.comentario = feedback.getComentario();
        this.idPessoa = feedback.getPessoa().getId();
    }

    public Feedback toEntity() {
        Feedback feedback = new Feedback(this.comentario);
        return feedback;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }
}

