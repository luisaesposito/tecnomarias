package br.uff.tecnomarias.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String comentario;

    @NotNull(message = "Nota é obrigatório")
    @Max(value = 5)
    private int nota;

    @NotNull(message = "Data de avaliacao é obrigatorio")
    private LocalDateTime data;

    @OneToOne
    @JoinColumn(name = "avaliadora_id")
    @NotNull(message = "Avaliadora é obrigatorio")
    private PessoaFisica avaliadora;

    private Boolean anonima = true;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private PessoaJuridica empresa;

    public Avaliacao() {
        //Deserialization.
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

    public PessoaFisica getAvaliadora() {
        return avaliadora;
    }

    public void setAvaliadora(PessoaFisica avaliadora) {
        this.avaliadora = avaliadora;
    }

    public Boolean getAnonima() {
        return anonima;
    }

    public void setAnonima(Boolean anonima) {
        this.anonima = anonima;
    }

    public PessoaJuridica getEmpresa() {
        return empresa;
    }

    public void setEmpresa(PessoaJuridica empresa) {
        this.empresa = empresa;
    }
}
