package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.Pessoa;
import br.uff.tecnomarias.domain.enums.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Set;
import java.util.stream.Collectors;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "tipoPessoa", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PessoaFisicaDTO.class, name = "PF"),
        @JsonSubTypes.Type(value = PessoaJuridicaDTO.class, name = "PJ")
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class PessoaDTO {

    private Long id;
    private TipoPessoa tipoPessoa;
    private String nome;
    private String email;
    private Set<TelefoneDTO> telefoneList;

    public PessoaDTO() { }

    public PessoaDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.email = pessoa.getEmail();
        this.tipoPessoa = pessoa.getTipoPessoa();
        this.telefoneList = pessoa.getTelefoneSet().stream().map(TelefoneDTO::new).collect(Collectors.toSet());
    }

    public void gerarPessoa(Pessoa pessoa) {
        pessoa.setNome(this.nome);
        pessoa.setEmail(this.email);
        pessoa.setTipoPessoa(this.tipoPessoa);
        if (this.telefoneList != null && !this.telefoneList.isEmpty())
            pessoa.setTelefoneSet(this.telefoneList.stream().map(TelefoneDTO::toEntity).collect(Collectors.toSet()));
    }

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

    public Set<TelefoneDTO> getTelefoneList() {
        return telefoneList;
    }

    public void setTelefoneList(Set<TelefoneDTO> telefoneList) {
        this.telefoneList = telefoneList;
    }
}
