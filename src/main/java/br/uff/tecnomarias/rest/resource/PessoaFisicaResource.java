package br.uff.tecnomarias.rest.resource;

import br.uff.tecnomarias.domain.entity.PessoaFisica;
import br.uff.tecnomarias.rest.dto.PessoaFisicaDTO;
import br.uff.tecnomarias.service.PessoaFisicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Cria uma nova pessoa fisica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PF criada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PessoaFisicaDTO.class))})
    })
    @PostMapping
    @ResponseBody
    public PessoaFisicaDTO criarPF(@RequestBody PessoaFisicaDTO pf) {
        return new PessoaFisicaDTO(pfService.salvar(pf.toEntity()));
    }

    @Operation(summary = "Lista todas as PFs cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de PFs",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PessoaFisicaDTO.class)))})
    })
    @GetMapping
    @ResponseBody
    public List<PessoaFisicaDTO> buscarTodas() {
        List<PessoaFisica> pfList = pfService.buscarTodas();
        return PessoaFisicaDTO.toDTOList(pfList);
    }

    @Operation(summary = "Busca uma PF por seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PF encontrada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PessoaFisicaDTO.class))}),
            @ApiResponse(responseCode = "404", description = "PF não encontrada")
    })
    @GetMapping("{id}")
    @ResponseBody
    public PessoaFisicaDTO buscarPorId(@PathVariable Long id) {
        return new PessoaFisicaDTO(pfService.buscarPorId(id));
    }

    @Operation(summary = "Altera informações de PF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PF alterada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PessoaFisicaDTO.class))}),
            @ApiResponse(responseCode = "404", description = "PF não encontrada")
    })
    @PutMapping("{id}")
    @ResponseBody
    public PessoaFisicaDTO alterar(@PathVariable Long id,
                                   @RequestBody PessoaFisicaDTO pfDTO) {
        return new PessoaFisicaDTO(pfService.alterar(id, pfDTO.toEntity()));
    }

    @Operation(summary = "Remove uma PF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PF removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "PF não encontrada")
    })
    @DeleteMapping("{id}")
    @ResponseBody
    public String remover(@PathVariable Long id) {
        pfService.remover(id);
        return "Pessoa removida com sucesso.";
    }

}
