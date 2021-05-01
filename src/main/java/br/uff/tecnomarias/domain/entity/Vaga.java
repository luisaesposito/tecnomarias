package br.uff.tecnomarias.domain.entity;

import br.uff.tecnomarias.domain.enums.Cargo;
import br.uff.tecnomarias.domain.utils.EntityUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
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
        EntityUtils.setIfNotNull(this::setAreaAtuacao, vagaAtualizada.getAreaAtuacao());
        EntityUtils.setIfNotNull(this::setCargo, vagaAtualizada.getCargo());
        EntityUtils.setIfNotNull(this::setDescricao, vagaAtualizada.getDescricao());
        EntityUtils.setIfNotNull(this::setValor, vagaAtualizada.getValor());
        EntityUtils.setIfNotNull(this::setLocalidade, vagaAtualizada.getLocalidade());
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vaga )) return false;
        return id != null && id.equals(((Vaga) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
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
