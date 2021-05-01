package br.uff.tecnomarias.domain.entity;

import br.uff.tecnomarias.domain.enums.PorteEmpresa;
import br.uff.tecnomarias.domain.enums.TipoPessoa;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Entity
public class PessoaJuridica extends Pessoa {

    @Column(unique = true)
    @NotBlank(message = "CNPJ é obrigatório")
    @Size(min = 14, max = 14, message = "CNPJ deve ter 14 caracteres")
    private String cnpj;

    private String site;

    private String descricao;

    @NotNull(message = "PorteEmpresa é obrigatório")
    @Enumerated(EnumType.STRING)
    private PorteEmpresa porteEmpresa;

    @NotBlank(message = "AreaAtuacao é obrigatório")
    private String areaAtuacao;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes;

    @Transient
    private Double mediaAvaliacao;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    public PessoaJuridica() {
        super.setTipoPessoa(TipoPessoa.PJ);
    }

    public PessoaJuridica atualizarDados(@Valid final PessoaJuridica pjAtualizada) {
        super.atualizarDados(pjAtualizada);
        setIfNotNull(this::setCnpj, pjAtualizada.getCnpj());
        setIfNotNull(this::setSite, pjAtualizada.getSite());
        setIfNotNull(this::setAreaAtuacao, pjAtualizada.getAreaAtuacao());
        setIfNotNull(this::setDescricao, pjAtualizada.getDescricao());
        setIfNotNull(this::setPorteEmpresa, pjAtualizada.getPorteEmpresa());
        setIfNotNull(this::setEndereco, pjAtualizada.getEndereco());
        return this;
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
        if (this.avaliacoes == null || this.avaliacoes.isEmpty())
            return Double.NaN;
        BigDecimal media = BigDecimal.valueOf(this.getAvaliacoes().stream().mapToDouble(Avaliacao::getNota).average().getAsDouble())
                .setScale(2, RoundingMode.FLOOR);
        return media.doubleValue();
    }

    private void setMediaAvaliacao(Double mediaAvaliacao) {
        this.mediaAvaliacao = mediaAvaliacao;
    }

    public void addAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
