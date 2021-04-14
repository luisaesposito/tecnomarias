package br.uff.tecnomarias.rest.resource;

import br.uff.tecnomarias.domain.entity.PessoaFisica;
import br.uff.tecnomarias.rest.dto.PessoaFisicaDTO;
import br.uff.tecnomarias.service.PessoaFisicaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pf")
@Tag(name = "Pessoa Fisica")
@CrossOrigin
public class PessoaFisicaResource {

    @Autowired
    PessoaFisicaService pfService;

    @PostMapping
    @ResponseBody
    public PessoaFisicaDTO criarPF(@RequestBody PessoaFisicaDTO pf) {
        return new PessoaFisicaDTO(pfService.salvar(pf.toEntity()));
    }

    @GetMapping
    @ResponseBody
    public List<PessoaFisicaDTO> buscarTodas() {
        List<PessoaFisica> pfList = pfService.buscarTodas();
        return PessoaFisicaDTO.toDTOList(pfList);
    }

    @GetMapping("{id}")
    @ResponseBody
    public PessoaFisicaDTO buscarPorId(@PathVariable Long id) {
        return new PessoaFisicaDTO(pfService.buscarPorId(id));
    }

    @PutMapping("{id}")
    @ResponseBody
    public PessoaFisicaDTO alterar(@PathVariable Long id,
                                   @RequestBody PessoaFisicaDTO pfDTO) {
        return new PessoaFisicaDTO(pfService.alterar(id, pfDTO.toEntity()));
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public String remover(@PathVariable Long id) {
        pfService.remover(id);
        return "Pessoa removida com sucesso.";
    }

}
