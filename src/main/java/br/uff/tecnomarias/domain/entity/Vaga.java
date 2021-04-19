package br.uff.tecnomarias.domain.entity;

import br.uff.tecnomarias.domain.enums.Cargo;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private PessoaJuridica empresa;

    @NotBlank
    private String areaAtuacao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @NotBlank
    private String descricao;

    private Double valor;

    private String localidade;

    public Vaga() {
    }

    public Vaga atualizarDados(@Valid final Vaga vagaAtualizada) {
        this.areaAtuacao = vagaAtualizada.getAreaAtuacao();
        this.cargo = vagaAtualizada.getCargo();
        this.descricao = vagaAtualizada.getDescricao();
        this.valor = vagaAtualizada.getValor();
        this.localidade = vagaAtualizada.getLocalidade();
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PessoaJuridica getEmpresa() {
        return empresa;
    }

    public void setEmpresa(PessoaJuridica empresa) {
        this.empresa = empresa;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
}
