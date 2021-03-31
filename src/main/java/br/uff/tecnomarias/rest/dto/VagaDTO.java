package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.entity.Vaga;

import java.util.List;
import java.util.stream.Collectors;

public class VagaDTO {

    private Long id;
    private Long idEmpresa;
    private String areaAtuacao;
    private String cargo;
    private String descricao;
    private Double valor;

    public VagaDTO() {}

    public VagaDTO(Vaga vaga) {
        this.id = vaga.getId();
        this.idEmpresa = vaga.getEmpresa().getId();
        this.areaAtuacao = vaga.getAreaAtuacao();
        this.cargo = vaga.getCargo();
        this.descricao = vaga.getDescricao();
        this.valor = vaga.getValor();
    }

    public Vaga toEntity() {
        Vaga vaga = new Vaga();
        PessoaJuridica empresa = new PessoaJuridica();
        empresa.setId(this.idEmpresa);
        vaga.setEmpresa(empresa);
        vaga.setAreaAtuacao(this.areaAtuacao);
        vaga.setCargo(this.cargo);
        vaga.setDescricao(this.descricao);
        vaga.setValor(this.valor);
        return vaga;
    }

    public static List<VagaDTO> toDTOList(List<Vaga> vagas) {
        return vagas.stream().map(vaga -> new VagaDTO(vaga)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
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
}
