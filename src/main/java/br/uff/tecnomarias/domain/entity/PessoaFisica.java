package br.uff.tecnomarias.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import java.io.Serializable;

@Entity
@DiscriminatorValue("PF")
public class PessoaFisica extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @OneToOne(cascade = CascadeType.ALL)
    private Links links;

    public PessoaFisica() {
    }

    public PessoaFisica atualizarDados(@Valid final PessoaFisica pfAtualizada) {
        //TODO usar setIfNotNull
        this.links = pfAtualizada.getLinks();
        return this;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
