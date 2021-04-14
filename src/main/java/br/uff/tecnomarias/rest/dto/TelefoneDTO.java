package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.Pessoa;
import br.uff.tecnomarias.domain.entity.Telefone;

public class TelefoneDTO {

    public Long id;
    public String ddi;
    public String ddd;
    public String numero;
    public Long idPessoa;

    public TelefoneDTO() { }

    public TelefoneDTO(Telefone telefone) {
        this.id = telefone.getId();
        this.ddi = telefone.getDdi();
        this.ddd = telefone.getDdd();
        this.numero = telefone.getNumero();
        if (telefone.getPessoa() != null)
            this.idPessoa = telefone.getPessoa().getId();
    }

    public Telefone toEntity() {
        Telefone telefone = new Telefone();
        telefone.setDdi(this.ddi);
        telefone.setDdd(this.ddd);
        telefone.setNumero(this.numero);
        Pessoa pessoa = new Pessoa();
        pessoa.setId(this.id);
        telefone.setPessoa(pessoa);
        return telefone;
    }
}
