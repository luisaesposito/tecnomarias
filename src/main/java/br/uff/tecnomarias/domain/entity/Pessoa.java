package br.uff.tecnomarias.domain.entity;

import br.uff.tecnomarias.domain.enums.TipoPessoa;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="TIPO_PESSOA", discriminatorType = DiscriminatorType.STRING)
public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @NotNull(message = "nome é obrigatório")
    protected String nome;

    @NotNull(message = "email é obrigatório")
    protected String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    protected List<Telefone> telefoneList = new ArrayList<>();

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

    public List<Telefone> getTelefones() {
        return telefoneList;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefoneList = telefones;
    }

    public boolean addTelefone(@Valid Telefone telefone) {
        if (this.telefoneList.stream().anyMatch(tel -> tel.getNumeroCompleto().equals(telefone.getNumeroCompleto())))
            return false;
        this.telefoneList.add(telefone);
        return true;
    }

    public boolean removeTelefone(Telefone telefone) {
        Optional<Telefone> telSalvo = this.telefoneList.stream()
                .filter(tel -> tel.getNumeroCompleto().equals(telefone.getNumeroCompleto()))
                .findFirst();
        if (!telSalvo.isPresent()) {
            this.telefoneList.remove(telSalvo.get());
            return true;
        }
        return false;
    }

    public <T> void setIfNotNull(final Consumer<T> setter, final T value) {
        if (value != null) {
            setter.accept(value);
        }
    }
}