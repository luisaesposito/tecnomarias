package br.uff.tecnomarias.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotBlank(message = "Comentario é obrigatório")
    private String comentario;

    @NotNull(message = "Nota é obrigatório")
    @Size(max = 5)
    private Double nota;

    @NotNull(message = "Data de avaliacao é obrigatorio")
    private LocalDateTime data;

    private String nomeAvaliadora;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private PessoaJuridica empresa;

    public Avaliacao() {
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getNomeAvaliadora() {
        return nomeAvaliadora;
    }

    public void setNomeAvaliadora(String avaliadora) {
        this.nomeAvaliadora = avaliadora;
    }

    public PessoaJuridica getEmpresa() {
        return empresa;
    }

    public void setEmpresa(PessoaJuridica empresa) {
        this.empresa = empresa;
    }
}
