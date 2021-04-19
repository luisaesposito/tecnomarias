package br.uff.tecnomarias.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
public class PessoaFisica extends Pessoa {

    @OneToOne(cascade = CascadeType.ALL)
    private Links links;

    public PessoaFisica() { }

    public void atualizarDados(@Valid PessoaFisica pf) {
        super.atualizarDados(pf);
        if (this.links != null)
            this.links.atualizarLinks(pf.getLinks());
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
