package br.uff.tecnomarias.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotBlank
    @Column(length = 500, nullable = false)
    private String comentario;

    @OneToOne(mappedBy = "feedback")
    private PessoaFisica pessoa;

    public Feedback() {
    }

    public Feedback(String comentario) {
        this.comentario = comentario;
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

    public PessoaFisica getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaFisica pessoa) {
        this.pessoa = pessoa;
    }
}
