package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.enums.PorteEmpresa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PessoaJuridicaDTO extends PessoaDTO{
    private Long id;
    private String cnpj;
    private String site;
    private String descricao;
    private String porteEmpresa;
    private String areaAtuacao;
//    private List<AvaliacaoDTO> avaliacoes;
    private Double mediaAvaliacao;
//    private Endereco endereco;

    public PessoaJuridicaDTO() {
        super();
    }

    public PessoaJuridicaDTO(PessoaJuridica pj) {
        super(pj);
        this.id = pj.getId();
        this.cnpj = pj.getCnpj();
        this.site = pj.getSite();
        this.descricao = pj.getDescricao();
        this.porteEmpresa = pj.getPorteEmpresa().toString();
        this.areaAtuacao = pj.getAreaAtuacao();
//        if (pj.getAvaliacoes() != null && !pj.getAvaliacoes().isEmpty()) {
//            this.avaliacoes = new ArrayList<>();
//            this.avaliacoes.addAll(pj.getAvaliacoes().stream()
//                    .map(av -> new AvaliacaoDTO(av))
//                    .collect(Collectors.toList()));
//        }
        this.mediaAvaliacao = pj.getMediaAvaliacao();
//        this.endereco = pj.getEndereco();
    }

    public PessoaJuridica toEntity() {
        PessoaJuridica pj = new PessoaJuridica();
        pj.setCnpj(this.cnpj);
        pj.setSite(this.site);
        pj.setDescricao(this.descricao);
        pj.setPorteEmpresa(PorteEmpresa.valueOf(this.porteEmpresa));
        pj.setAreaAtuacao(this.areaAtuacao);
//        pj.setEndereco(this.endereco);
        gerarPessoa(pj);
        return pj;
    }

    public static List<PessoaJuridicaDTO> toDTOList(List<PessoaJuridica> pjList) {
        return pjList.stream().map(pj -> new PessoaJuridicaDTO(pj)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPorteEmpresa() {
        return porteEmpresa;
    }

    public void setPorteEmpresa(String porteEmpresa) {
        this.porteEmpresa = porteEmpresa;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

//    public List<AvaliacaoDTO> getAvaliacoes() {
//        return avaliacoes;
//    }
//
//    public void setAvaliacoes(List<AvaliacaoDTO> avaliacoes) {
//        this.avaliacoes = avaliacoes;
//    }

    public Double getMediaAvaliacao() {
        return mediaAvaliacao;
    }

    public void setMediaAvaliacao(Double mediaAvaliacao) {
        this.mediaAvaliacao = mediaAvaliacao;
    }

//    public Endereco getEndereco() {
//        return endereco;
//    }
//
//    public void setEndereco(Endereco endereco) {
//        this.endereco = endereco;
//    }
}
