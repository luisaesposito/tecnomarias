package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.Avaliacao;
import br.uff.tecnomarias.domain.entity.PessoaJuridica;

import java.time.LocalDateTime;

public class AvaliacaoDTO {

    public Long id;
    public String comentario;
    public Double nota;
    public LocalDateTime timestamp;
    public String nomeAvaliadora;
    public Long idEmpresa;

    public AvaliacaoDTO() { }

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
        avaliacao.setTimestamp(this.timestamp);
        avaliacao.setNomeAvaliadora(this.nomeAvaliadora);
        PessoaJuridica empresa = new PessoaJuridica();
        empresa.setId(this.id);
        avaliacao.setEmpresa(empresa);
        return avaliacao;
    }
}
