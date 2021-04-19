package br.uff.tecnomarias.domain.entity;

import br.uff.tecnomarias.domain.enums.TipoPessoa;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @NotNull(message = "nome é obrigatório")
    protected String nome;

    @NotNull(message = "email é obrigatório")
    protected String email;

    @NotNull(message = "tipo pessoa é obrigatório")
    @Enumerated(EnumType.STRING)
    protected TipoPessoa tipoPessoa;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    protected List<Telefone> telefoneList = new ArrayList<>();

    public void atualizarDados(@Valid Pessoa pessoa) {
        setIfNotNull(this::setNome, pessoa.getNome());
        setIfNotNull(this::setEmail, pessoa.getEmail());
        setIfNotNull(this::setTipoPessoa, pessoa.getTipoPessoa());
        this.telefoneList = pessoa.telefoneList;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Telefone> getTelefoneList() {
        return telefoneList;
    }

    public void setTelefoneList(List<Telefone> telefoneList) {
        this.telefoneList = telefoneList;
    }

    public void addTelefone(Telefone telefone) {
        this.telefoneList.add(telefone);
    }

    public <T> void setIfNotNull(final Consumer<T> setter, final T value) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
