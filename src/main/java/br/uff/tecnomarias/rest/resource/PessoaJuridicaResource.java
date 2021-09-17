package br.uff.tecnomarias.rest.resource;

import br.uff.tecnomarias.rest.dto.AvaliacaoDTO;
import br.uff.tecnomarias.rest.dto.PessoaJuridicaDTO;
import br.uff.tecnomarias.service.PessoaJuridicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("empresa")
@Tag(name = "Pessoa Juridica")
@CrossOrigin
public class PessoaJuridicaResource {

    @Autowired
    PessoaJuridicaService pjService;

    @Operation(summary = "Cria uma nova empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PJ criada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PessoaJuridicaDTO.class))})
    })
    @PostMapping
    @ResponseBody
    public ResponseEntity<PessoaJuridicaDTO >salvarPJ(@RequestBody PessoaJuridicaDTO pjDTO) {
        PessoaJuridicaDTO pjCriada = new PessoaJuridicaDTO(pjService.salvar(pjDTO.toEntity()));
        return new ResponseEntity<>(pjCriada, HttpStatus.CREATED);
    }

    @Operation(summary = "Busca uma empresa pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PJ encontrada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PessoaJuridicaDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Empresa nao encontrada", content = @Content)
    })
    @PutMapping("{id}")
    @ResponseBody
    public PessoaJuridicaDTO alterar(@PathVariable("id") final Long id,
                                     @RequestBody PessoaJuridicaDTO pjDTO) {
        return new PessoaJuridicaDTO(pjService.alterar(id, pjDTO.toEntity()));
    }

    @Operation(summary = "Lista todas as empresas cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de PJs",
            content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PessoaJuridicaDTO.class)))})
    @GetMapping
    @ResponseBody
    public List<PessoaJuridicaDTO> buscarTodas() {
        return PessoaJuridicaDTO.toDTOList(pjService.buscarTodas());
    }

    @Operation(summary = "Busca uma empresa pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PJ encontrada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PessoaJuridicaDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Empresa nao encontrada", content = @Content)
    })
    @GetMapping("{id}")
    @ResponseBody
    public PessoaJuridicaDTO buscarPorId(@PathVariable("id") final Long id) {
        return new PessoaJuridicaDTO(pjService.buscarPorId(id));
    }

    @Operation(summary = "Remova uma empresa pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PJ removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa nao encontrada", content = @Content)
    })
    @DeleteMapping("{id}")
    @ResponseBody
    public String removerVaga(@PathVariable Long id) {
        pjService.remover(id);
        return "Vaga removida com sucesso.";
    }

    @Operation(summary = "Adiciona uma avaliacao para uma empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliacao criada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa nao encontrada", content = @Content)
    })
    @PostMapping("{id}/avaliacao")
    @ResponseBody
    public AvaliacaoDTO avaliarEmpresa(@PathVariable("id") final Long id, @RequestBody AvaliacaoDTO avaliacaoDTO) {
        return new AvaliacaoDTO(pjService.avaliarEmpresa(id, avaliacaoDTO.toEntity()));
    }

    @Operation(summary = "Remove uma avaliacao pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliacao removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Avaliacao nao encontrada", content = @Content)
    })
    @DeleteMapping("avaliacao/{id}")
    @ResponseBody
    public String removerAvaliacao(@PathVariable("id") Long id) {
        pjService.removerAvaliacao(id);
        return "Avaliação removida com sucesso.";
    }
}
