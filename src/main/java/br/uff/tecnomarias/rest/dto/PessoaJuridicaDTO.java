package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.enums.PorteEmpresa;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PessoaJuridicaDTO extends PessoaDTO {

    private String cnpj;
    private String site;
    private String descricao;
    private PorteEmpresa porteEmpresa;
    private String areaAtuacao;
    private List<AvaliacaoDTO> avaliacoes;
    private Double mediaAvaliacao;
    private EnderecoDTO endereco;

    public PessoaJuridicaDTO() {
        super();
    }

    public PessoaJuridicaDTO(PessoaJuridica pj) {
        super(pj);
        this.cnpj = pj.getCnpj();
        this.site = pj.getSite();
        this.descricao = pj.getDescricao();
        this.porteEmpresa = pj.getPorteEmpresa();
        this.areaAtuacao = pj.getAreaAtuacao();
        if (Objects.nonNull(pj.getAvaliacoes()))
            this.avaliacoes = pj.getAvaliacoes().stream().map(av -> new AvaliacaoDTO(av)).collect(Collectors.toList());
        this.mediaAvaliacao = pj.getMediaAvaliacao();
        if (pj.getEndereco() != null)
            this.endereco = new EnderecoDTO(pj.getEndereco());
    }

    public PessoaJuridica toEntity() {
        PessoaJuridica pj = new PessoaJuridica();
        pj.setCnpj(this.cnpj);
        pj.setSite(this.site);
        pj.setDescricao(this.descricao);
        pj.setPorteEmpresa(this.porteEmpresa);
        pj.setAreaAtuacao(this.areaAtuacao);
        if (this.endereco != null)
            pj.setEndereco(this.endereco.toEntity());
        gerarPessoa(pj);
        return pj;
    }

    public static List<PessoaJuridicaDTO> toDTOList(List<PessoaJuridica> pjList) {
        return pjList.stream().map(pj -> new PessoaJuridicaDTO(pj)).collect(Collectors.toList());
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

    public List<AvaliacaoDTO> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<AvaliacaoDTO> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public Double getMediaAvaliacao() {
        return mediaAvaliacao;
    }

    public void setMediaAvaliacao(Double mediaAvaliacao) {
        this.mediaAvaliacao = mediaAvaliacao;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
