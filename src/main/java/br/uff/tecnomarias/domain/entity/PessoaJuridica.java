package br.uff.tecnomarias.domain.entity;

import br.uff.tecnomarias.domain.enums.PorteEmpresa;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "PessoaJuridica.findByCnpj", query = "SELECT pj FROM PessoaJuridica pj WHERE pj.cnpj LIKE :cnpj")
})
public class PessoaJuridica extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(unique = true)
    @NotNull(message = "CNPJ é obrigatório")
    @Size(min = 14, max = 14, message = "CNPJ deve ter 14 caracteres")
    private String cnpj;

    private String site;

    private String descricao;

    @NotNull(message = "PorteEmpresa é obrigatório")
    @Enumerated(EnumType.STRING)
    private PorteEmpresa porteEmpresa;

    @NotNull(message = "AreaAtuacao é obrigatório")
    private String areaAtuacao;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.REMOVE)
    private List<Avaliacao> avaliacoes;

    private Double mediaAvaliacao;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.REMOVE)
    private List<Vaga> vagas;

    public PessoaJuridica() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PorteEmpresa getPorteEmpresa() {
        return porteEmpresa;
    }

    public void setPorteEmpresa(PorteEmpresa porteEmpresa) {
        this.porteEmpresa = porteEmpresa;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public Double getMediaAvaliacao() {
        return mediaAvaliacao;
    }

    public void setMediaAvaliacao() {
        if (this.avaliacoes != null) {
            this.mediaAvaliacao = this.getAvaliacoes().stream()
                    .mapToDouble(Avaliacao::getNota).average().orElse(Double.NaN);
        } else {
            this.mediaAvaliacao = Double.NaN;
        }
    }

    public List<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(List<Vaga> vagas) {
        this.vagas = vagas;
    }

    public void addAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
    }
}
