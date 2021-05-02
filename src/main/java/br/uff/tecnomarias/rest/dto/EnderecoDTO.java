package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.Endereco;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnderecoDTO {

    public Long id;
    public String logradouro;
    public String numero;
    public String complemento;
    public String bairro;
    public Integer municipioIBGE;

    public EnderecoDTO() {
    }

    public EnderecoDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.municipioIBGE = endereco.getMunicipioIBGE();
    }

    public Endereco toEntity() {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(this.logradouro);
        endereco.setNumero(this.numero);
        endereco.setComplemento(this.complemento);
        endereco.setBairro(this.bairro);
        endereco.setMunicipioIBGE(this.municipioIBGE);
        return endereco;
    }
}
