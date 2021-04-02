package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.Avaliacao;
import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.entity.Vaga;
import br.uff.tecnomarias.domain.enums.PorteEmpresa;

import java.util.List;
import java.util.stream.Collectors;

public class PessoaJuridicaDTO {

    private String cnpj;
    private String site;
    private String descricao;
    private PorteEmpresa porteEmpresa;
    private String areaAtuacao;
    private List<Avaliacao> avaliacoes;
    private Double mediaAvaliacao;
    private List<Vaga> vagas;

    public PessoaJuridicaDTO() {
    }

    public PessoaJuridicaDTO(PessoaJuridica pj) {
        this.cnpj = pj.getCnpj();
        this.site = pj.getSite();
        this.descricao = pj.getDescricao();
        this.porteEmpresa = pj.getPorteEmpresa();
        this.areaAtuacao = pj.getAreaAtuacao();
        this.avaliacoes = pj.getAvaliacoes();
        this.mediaAvaliacao = pj.getMediaAvaliacao();
    }

    public PessoaJuridica toEntity() {
        PessoaJuridica pj = new PessoaJuridica();
        pj.setCnpj(this.cnpj);
        pj.setSite(this.site);
        pj.setDescricao(this.descricao);
        pj.setPorteEmpresa(this.porteEmpresa);
        pj.setAreaAtuacao(this.areaAtuacao);
        pj.setAvaliacoes(this.avaliacoes);
        pj.setMediaAvaliacao(this.mediaAvaliacao);
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

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public Double getMediaAvaliacao() {
        return mediaAvaliacao;
    }

    public void setMediaAvaliacao(Double mediaAvaliacao) {
        this.mediaAvaliacao = mediaAvaliacao;
    }

    public List<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(List<Vaga> vagas) {
        this.vagas = vagas;
    }
}
