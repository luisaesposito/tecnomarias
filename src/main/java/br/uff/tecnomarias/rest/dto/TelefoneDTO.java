package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.Telefone;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TelefoneDTO {

    public Long id;
    public String ddi;
    public String ddd;
    public String numero;
    public String numeroCompleto;

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
}
