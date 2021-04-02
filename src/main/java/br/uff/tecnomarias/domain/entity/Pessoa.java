package br.uff.tecnomarias.domain.entity;

import br.uff.tecnomarias.domain.enums.TipoPessoa;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message="Tipo de pessoa deve ser definido")
    private TipoPessoa tipoPessoa;

    @NotNull(message = "nome é obrigatório")
    private String nome;

    @NotNull(message = "email é obrigatório")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<Telefone> telefoneList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
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