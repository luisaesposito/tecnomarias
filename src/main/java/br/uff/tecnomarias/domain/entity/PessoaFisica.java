package br.uff.tecnomarias.domain.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PessoaFisica extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @OneToOne(cascade = CascadeType.ALL)
    @Column(name = "id_links")
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
