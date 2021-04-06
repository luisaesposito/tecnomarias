package br.uff.tecnomarias.domain.entity;

import br.uff.tecnomarias.domain.enums.TipoEndereco;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoEndereco tipoEndereco;

    @NotNull(message = "Logradouro é obrigatorio")
    @Column(nullable = false)
    private String logradouro;

    @NotNull(message = "Numero é obrigatorio")
    @Column(nullable = false)
    private String numero;

    private String complemento;

    private String bairro;

    @NotNull(message = "Codigo IBGE do municipio é obrigatorio")
    @Column(nullable = false)
    private Integer municipioIBGE;

    public Endereco() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
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
