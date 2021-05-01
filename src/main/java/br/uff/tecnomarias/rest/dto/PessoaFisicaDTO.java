package br.uff.tecnomarias.rest.dto;

import br.uff.tecnomarias.domain.entity.Links;
import br.uff.tecnomarias.domain.entity.PessoaFisica;

import java.util.List;
import java.util.stream.Collectors;

public class PessoaFisicaDTO extends PessoaDTO {

    public Links links;

    public PessoaFisicaDTO() {
        super();
    }

    public PessoaFisicaDTO(PessoaFisica pf) {
        super(pf);
        this.links = pf.getLinks();
    }

    public PessoaFisica toEntity() {
        PessoaFisica pf = new PessoaFisica();
        pf.setLinks(this.links);
        gerarPessoa(pf);
        return pf;
    }

    public static List<PessoaFisicaDTO> toDTOList(List<PessoaFisica> pfList) {
        return pfList.stream().map(PessoaFisicaDTO::new).collect(Collectors.toList());
    }
}
