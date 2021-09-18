package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.Links;
import br.uff.tecnomarias.domain.entity.PessoaFisica;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PessoaFisicaDTO extends PessoaDTO {

    private Links links;
    private LocalDate dataCadastro;

    public PessoaFisicaDTO() {
        super();
    }

    public PessoaFisicaDTO(PessoaFisica pf) {
        super(pf);
        this.links = pf.getLinks();
        this.dataCadastro = pf.getDataCadastro();
    }

    public PessoaFisica toEntity() {
        var pf = new PessoaFisica();
        pf.setLinks(this.links);
        gerarPessoa(pf);
        return pf;
    }

    public static List<PessoaFisicaDTO> toDTOList(List<PessoaFisica> pfList) {
        return pfList.stream().map(PessoaFisicaDTO::new).collect(Collectors.toList());
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
