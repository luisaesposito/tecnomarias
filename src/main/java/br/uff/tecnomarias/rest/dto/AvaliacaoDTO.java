package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.Avaliacao;

import java.time.LocalDateTime;

public class AvaliacaoDTO {
    private Long id;
    private String comentario;
    private Double nota;
    private LocalDateTime timestamp;
    private String nomeAvaliadora;
    private Long idEmpresa;

    public AvaliacaoDTO() {
    }

    public AvaliacaoDTO(Avaliacao avaliacao) {
        this.id = avaliacao.getId();
        this.comentario = avaliacao.getComentario();
        this.nota = avaliacao.getNota();
        this.timestamp = avaliacao.getTimestamp();
        this.nomeAvaliadora = avaliacao.getNomeAvaliadora();
        this.idEmpresa = avaliacao.getEmpresa().getId();
    }

    public Avaliacao toEntity() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setComentario(this.comentario);
        avaliacao.setNota(this.nota);
        avaliacao.setNomeAvaliadora(this.nomeAvaliadora);
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

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getNomeAvaliadora() {
        return nomeAvaliadora;
    }

    public void setNomeAvaliadora(String nomeAvaliadora) {
        this.nomeAvaliadora = nomeAvaliadora;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
