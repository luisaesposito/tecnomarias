package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.entity.Vaga;
import br.uff.tecnomarias.domain.enums.Cargo;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VagaDTO {

    private Long id;
    private Long idEmpresa;
    private String areaAtuacao;
    private Cargo cargo;
    private String descricao;
    private Double valor;
    private String localidade;

    public VagaDTO() {}

    public VagaDTO(Vaga vaga) {
        this.id = vaga.getId();
        this.idEmpresa = vaga.getEmpresa().getId();
        this.areaAtuacao = vaga.getAreaAtuacao();
        this.cargo = vaga.getCargo();
        this.descricao = vaga.getDescricao();
        this.valor = vaga.getValor();
        this.localidade = vaga.getLocalidade();
    }

    public Vaga toEntity() {
        var vaga = new Vaga();
        var empresa = new PessoaJuridica();
        empresa.setId(this.idEmpresa);
        vaga.setEmpresa(empresa);
        vaga.setAreaAtuacao(this.areaAtuacao);
        vaga.setCargo(this.cargo);
        vaga.setDescricao(this.descricao);
        vaga.setValor(this.valor);
        vaga.setLocalidade(this.localidade);
        return vaga;
    }

    public static List<VagaDTO> toDTOList(List<Vaga> vagas) {
        return vagas.stream().map(VagaDTO::new).collect(Collectors.toList());
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
