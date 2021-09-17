package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.Telefone;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TelefoneDTO {

    private Long id;
    private String ddi;
    private String ddd;
    private String numero;
    private String numeroCompleto;

    public TelefoneDTO() { }

    public TelefoneDTO(Telefone telefone) {
        this.id = telefone.getId();
        this.ddi = telefone.getDdi();
        this.ddd = telefone.getDdd();
        this.numero = telefone.getNumero();
        this.numeroCompleto = String.format("%s %s %s",telefone.getDdi(), telefone.getDdd(), telefone.getNumero());
    }

    public Telefone toEntity() {
        Telefone telefone = new Telefone();
        telefone.setDdi(this.ddi);
        telefone.setDdd(this.ddd);
        telefone.setNumero(this.numero);
        return telefone;
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
        return numeroCompleto;
    }

    public void setNumeroCompleto(String numeroCompleto) {
        this.numeroCompleto = numeroCompleto;
    }
}
