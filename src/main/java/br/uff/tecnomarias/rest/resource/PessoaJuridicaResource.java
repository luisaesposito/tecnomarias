package br.uff.tecnomarias.rest.resource;

import br.uff.tecnomarias.rest.dto.PessoaJuridicaDTO;
import br.uff.tecnomarias.service.PessoaJuridicaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("pj")
@Tag(name = "Pessoa Juridica")
@CrossOrigin
public class PessoaJuridicaResource {

    @Autowired
    PessoaJuridicaService pjService;

    @PostMapping
    @ResponseBody
    public PessoaJuridicaDTO salvarPJ(@RequestBody PessoaJuridicaDTO pjDTO) {
        return new PessoaJuridicaDTO(pjService.salvar(pjDTO.toEntity()));
    }

    @PutMapping("{id}")
    @ResponseBody
    public PessoaJuridicaDTO alterar(@PathVariable("id") final Long id,
                                     @RequestBody PessoaJuridicaDTO pjDTO) {
        return new PessoaJuridicaDTO(pjService.alterar(id, pjDTO.toEntity()));
    }

    @GetMapping
    @ResponseBody
    public List<PessoaJuridicaDTO> buscarTodas() {
        return PessoaJuridicaDTO.toDTOList(pjService.buscarTodas());
    }

    @GetMapping("{id}")
    @ResponseBody
    public PessoaJuridicaDTO buscarPorId(@PathVariable("id") final Long id) {
        return new PessoaJuridicaDTO(pjService.buscarPorId(id));
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public String removerVaga(@PathVariable Long id) {
        pjService.remover(id);
        return "Vaga removida com sucesso.";
    }

//    @POST
//    @Path("{id}/avaliacao")
//    public AvaliacaoDTO avaliarEmpresa(@PathParam("id") final Long id, AvaliacaoDTO avaliacaoDTO) {
//        //TODO implementar auth
//        AvaliacaoDTO resp = new AvaliacaoDTO(pjService.avaliarEmpresa(id, avaliacaoDTO.toEntity()));
//        return Response.ok(resp).build();
//    }
}
