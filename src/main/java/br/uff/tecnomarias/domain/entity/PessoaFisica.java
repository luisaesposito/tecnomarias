package br.uff.tecnomarias.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class PessoaFisica extends Pessoa {

    @OneToOne(cascade = CascadeType.ALL)
    private Links links;

    public PessoaFisica() {
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
