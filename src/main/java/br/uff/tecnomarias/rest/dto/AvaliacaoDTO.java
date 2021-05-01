package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.Avaliacao;
import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AvaliacaoDTO {

    public Long id;
    public String comentario;
    public Double nota;
    public LocalDateTime data;
    public String nomeAvaliadora;
    public Long idEmpresa;

    public AvaliacaoDTO() { }

    public AvaliacaoDTO(Avaliacao avaliacao) {
        this.id = avaliacao.getId();
        this.comentario = avaliacao.getComentario();
        this.nota = avaliacao.getNota();
        this.data = avaliacao.getData();
        this.nomeAvaliadora = avaliacao.getNomeAvaliadora();
        this.idEmpresa = avaliacao.getEmpresa().getId();
    }

    public Avaliacao toEntity() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setComentario(this.comentario);
        avaliacao.setNota(this.nota);
        avaliacao.setData(this.data);
        avaliacao.setNomeAvaliadora(this.nomeAvaliadora);
        PessoaJuridica empresa = new PessoaJuridica();
        empresa.setId(this.idEmpresa);
        avaliacao.setEmpresa(empresa);
        return avaliacao;
    }
}
