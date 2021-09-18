package br.uff.tecnomarias.domain.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

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

    public Telefone() {
        //Deserialization
    }

    public Telefone atualizar(@Valid Telefone telAtualizado) {
        this.ddi = telAtualizado.getDdi();
        this.ddd = telAtualizado.getDdd();
        this.numero = telAtualizado.getNumero();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var telefone = (Telefone) o;
        return Objects.equals(ddi, telefone.ddi) && Objects.equals(ddd, telefone.ddd) && Objects.equals(numero, telefone.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ddi, ddd, numero);
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

}
