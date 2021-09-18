package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.Avaliacao;
import br.uff.tecnomarias.domain.entity.PessoaFisica;
import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AvaliacaoDTO {

    private Long id;
    private String comentario;
    private int nota;
    private LocalDateTime data;
    private Long idAvaliadora;
    private Boolean anonima;
    private Long idEmpresa;

    public AvaliacaoDTO() { }

    public AvaliacaoDTO(Avaliacao avaliacao) {
        this.id = avaliacao.getId();
        this.comentario = avaliacao.getComentario();
        this.nota = avaliacao.getNota();
        this.data = avaliacao.getData();
        this.idAvaliadora = avaliacao.getAvaliadora().getId();
        this.anonima = avaliacao.getAnonima();
        this.idEmpresa = avaliacao.getEmpresa().getId();
    }

    public Avaliacao toEntity() {
        var avaliacao = new Avaliacao();
        avaliacao.setComentario(this.comentario);
        avaliacao.setNota(this.nota);
        avaliacao.setData(this.data);
        var avaliadora = new PessoaFisica();
        avaliadora.setId(this.idAvaliadora);
        avaliacao.setAvaliadora(avaliadora);
        var empresa = new PessoaJuridica();
        empresa.setId(this.idEmpresa);
        avaliacao.setEmpresa(empresa);
        return avaliacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
