package br.uff.tecnomarias.domain.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(length = 3)
    private String ddi;

    @Column(length = 2)
    @NotBlank(message = "DDD é obrigatório")
    @Size(min = 2,max = 2, message = "DDD deve possuir apenas 2 digitos")
    private String ddd;

    @Column(length = 10)
    @NotBlank(message = "Número do telefone é obrigatório")
    private String numero;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    public Telefone() { }

    public Telefone atualizar(@Valid Telefone telAtualizado) {
        this.ddi = telAtualizado.getDdi();
        this.ddd = telAtualizado.getDdd();
        this.numero = telAtualizado.getNumero();
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumeroCompleto() {
        return ddd.concat(numero);
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
