package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.Endereco;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnderecoDTO {

    private Long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private Integer municipioIBGE;

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
        var endereco = new Endereco();
        endereco.setLogradouro(this.logradouro);
        endereco.setNumero(this.numero);
        endereco.setComplemento(this.complemento);
        endereco.setBairro(this.bairro);
        endereco.setMunicipioIBGE(this.municipioIBGE);
        return endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getMunicipioIBGE() {
        return municipioIBGE;
    }

    public void setMunicipioIBGE(Integer municipioIBGE) {
        this.municipioIBGE = municipioIBGE;
    }
}
