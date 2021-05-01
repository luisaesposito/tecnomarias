package br.uff.tecnomarias.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
public class PessoaFisica extends Pessoa {

    @OneToOne(cascade = CascadeType.ALL)
    private Links links;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Feedback feedback;

    public PessoaFisica() {
    }

    public void atualizarDados(@Valid PessoaFisica pf) {
        super.atualizarDados(pf);
        if (pf.links != null)
            this.links.atualizarLinks(pf.getLinks());
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

}
