package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.entity.Vaga;
import br.uff.tecnomarias.domain.enums.Cargo;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VagaDTO {

    public Long id;
//    public Long idEmpresa;
    public String areaAtuacao;
    public Cargo cargo;
    public String descricao;
    public Double valor;
    public String localidade;

    public VagaDTO() {}

    public VagaDTO(Vaga vaga) {
        this.id = vaga.getId();
//        this.idEmpresa = vaga.getEmpresa().getId();
        this.areaAtuacao = vaga.getAreaAtuacao();
        this.cargo = vaga.getCargo();
        this.descricao = vaga.getDescricao();
        this.valor = vaga.getValor();
        this.localidade = vaga.getLocalidade();
    }

    public Vaga toEntity() {
        Vaga vaga = new Vaga();
//        PessoaJuridica empresa = new PessoaJuridica();
//        empresa.setId(this.idEmpresa);
//        vaga.setEmpresa(empresa);
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
}
