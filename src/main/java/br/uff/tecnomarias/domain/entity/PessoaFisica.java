package br.uff.tecnomarias.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
public class PessoaFisica extends Pessoa {

    @OneToOne(cascade = CascadeType.ALL)
    private Links links;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Feedback feedback;

    public PessoaFisica() {
    }

    public void atualizarDados(@Valid PessoaFisica pf) {
        super.atualizarDados(pf);
        if (this.links != null)
            this.links.atualizarLinks(pf.getLinks());
        if (this.feedback != null)
            this.feedback = pf.getFeedback();
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
