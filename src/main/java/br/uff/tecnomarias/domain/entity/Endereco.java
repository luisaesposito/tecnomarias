package br.uff.tecnomarias.domain.entity;

import br.uff.tecnomarias.domain.utils.EntityUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotBlank(message = "Logradouro é obrigatorio")
    private String logradouro;

    @NotBlank(message = "Numero é obrigatorio")
    private String numero;

    private String complemento;

    private String bairro;

    @NotNull(message = "Codigo IBGE do municipio é obrigatorio")
    private Integer municipioIBGE;

    public Endereco() {
        //Deserialization
    }

    public Endereco atualizar(Endereco enderecoAtualizado) {
        this.setLogradouro(enderecoAtualizado.getLogradouro());
        this.setNumero(enderecoAtualizado.getNumero());
        this.setComplemento(enderecoAtualizado.getComplemento());
        this.setBairro(enderecoAtualizado.getBairro());
        this.setMunicipioIBGE(enderecoAtualizado.getMunicipioIBGE());
        return this;
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
