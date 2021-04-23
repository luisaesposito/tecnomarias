package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.enums.PorteEmpresa;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PessoaJuridicaDTO extends PessoaDTO {

    public String cnpj;
    public String site;
    public String descricao;
    public PorteEmpresa porteEmpresa;
    public String areaAtuacao;
    public List<AvaliacaoDTO> avaliacoes;
    public Double mediaAvaliacao;
    public EnderecoDTO endereco;

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

}
