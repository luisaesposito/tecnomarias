package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.Endereco;
import br.uff.tecnomarias.domain.entity.Pessoa;
import br.uff.tecnomarias.domain.entity.Telefone;
import br.uff.tecnomarias.domain.enums.TipoPessoa;

import java.util.List;

//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "tipoPessoa", visible = true)
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = PessoaFisicaDTO.class, name = "PESSOA_FISICA"),
//        @JsonSubTypes.Type(value = PessoaJuridicaDTO.class, name = "PESSOA_JURIDICA")
//})
public abstract class PessoaDTO {

    private String nome;
    private String email;
    private List<Telefone> telefoneList;

    public PessoaDTO() {
    }

    public PessoaDTO(Pessoa pessoa) {
        this.email = pessoa.getEmail();
        this.telefoneList = pessoa.getTelefones();
    }
    public void gerarPessoa(Pessoa pessoa) {
        pessoa.setEmail(this.email);
        pessoa.setTelefones(this.telefoneList);
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

}
