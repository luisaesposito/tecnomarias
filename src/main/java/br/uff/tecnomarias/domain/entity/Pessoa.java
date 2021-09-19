package br.uff.tecnomarias.domain.entity;

import br.uff.tecnomarias.domain.enums.TipoPessoa;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @NotBlank(message = "nome é obrigatório")
    protected String nome;

    @NotBlank(message = "email é obrigatório")
    protected String email;

    @NotNull(message = "tipo pessoa é obrigatório")
    @Enumerated(EnumType.STRING)
    protected TipoPessoa tipoPessoa;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id")
    protected Set<Telefone> telefoneSet = new HashSet<>();

    public void atualizarDados(@Valid Pessoa pessoa) {
        this.setNome(pessoa.getNome());
        this.setEmail(pessoa.getEmail());
        this.setTipoPessoa(pessoa.getTipoPessoa());
        if (pessoa.getTelefoneSet() != null) {
            this.getTelefoneSet().clear();
            if (!pessoa.getTelefoneSet().isEmpty())
                telefoneSet.addAll(pessoa.getTelefoneSet());
        }
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

    public Set<Telefone> getTelefoneSet() {
        return telefoneSet;
    }

    public void setTelefoneSet(Set<Telefone> telefoneSet) {
        this.telefoneSet = telefoneSet;
    }

}
