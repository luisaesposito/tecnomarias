package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.Pessoa;
import br.uff.tecnomarias.domain.enums.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;
import java.util.stream.Collectors;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "tipoPessoa", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PessoaFisicaDTO.class, name = "PF"),
        @JsonSubTypes.Type(value = PessoaJuridicaDTO.class, name = "PJ")
})
public abstract class PessoaDTO {

    public Long id;
    public TipoPessoa tipoPessoa;
    public String nome;
    public String email;
    public List<TelefoneDTO> telefoneList;

    public PessoaDTO() { }

    public PessoaDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.email = pessoa.getEmail();
        this.tipoPessoa = pessoa.getTipoPessoa();
        this.telefoneList = pessoa.getTelefoneList().stream().map(TelefoneDTO::new).collect(Collectors.toList());
    }

    public void gerarPessoa(Pessoa pessoa) {
        pessoa.setNome(this.nome);
        pessoa.setEmail(this.email);
        pessoa.setTipoPessoa(this.tipoPessoa);
        if (this.telefoneList != null && !this.telefoneList.isEmpty())
            pessoa.setTelefoneList(this.telefoneList.stream().map(TelefoneDTO::toEntity).collect(Collectors.toList()));
    }
}
