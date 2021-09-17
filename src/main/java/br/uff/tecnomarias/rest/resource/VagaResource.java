package br.uff.tecnomarias.rest.resource;

import br.uff.tecnomarias.domain.entity.Vaga;
import br.uff.tecnomarias.domain.enums.Cargo;
import br.uff.tecnomarias.rest.dto.VagaDTO;
import br.uff.tecnomarias.service.VagaService;
import br.uff.tecnomarias.service.exception.BadRequestException;
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

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("vaga")
@Tag(name = "Vaga")
@CrossOrigin
public class VagaResource {

    @Autowired
    VagaService vagaService;

    @Operation(summary = "Cria uma nova vaga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vaga criada com sucesso",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VagaDTO.class))})
    })
    @PostMapping
    @ResponseBody
    public ResponseEntity<VagaDTO> criarVaga(@RequestBody VagaDTO vagaDTO) {
        VagaDTO vaga = new VagaDTO(vagaService.salvar(vagaDTO.toEntity()));
        return new ResponseEntity<>(vaga, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todas as vagas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vagas encontradas",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = VagaDTO.class)))})
    })
    @GetMapping
    @ResponseBody
    public List<VagaDTO> buscarTodas() {
        List<Vaga> vagas = vagaService.buscarTodas();//start, pageSize);
        return VagaDTO.toDTOList(vagas);
    }

    @Operation(summary = "Busca uma vaga por seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vaga encontrada",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VagaDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Vaga nao encontrada", content = @Content)
    })
    @GetMapping("{id}")
    @ResponseBody
    public VagaDTO buscarPorId(@PathVariable Long id) {
        return new VagaDTO(vagaService.buscarPorId(id));
    }

    @Operation(summary = "Retorna a quantidade total de vagas cadastradas")
    @ApiResponse(responseCode = "200", description = "Vaga encontrada")
    @GetMapping("count")
    @ResponseBody
    public Long buscarTotal() {
        return vagaService.count();
    }

    @Operation(summary = "Lista vagas de uma empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista das vagas da empresa cujo id foi informado",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = VagaDTO.class)))}),
            @ApiResponse(responseCode = "404", description = "Vaga nao encontrada", content = @Content)
    })
    @GetMapping("empresa/{id}")
    @ResponseBody
    public List<VagaDTO> buscarPorEmpresa(@PathVariable Long id) {
        List<Vaga> vagas = vagaService.buscarPorEmpresa(id);
        return VagaDTO.toDTOList(vagas);
    }

    @Operation(summary = "Busca vagas por um atributo (areaAtuacao, cargo ou localidade)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vagas encontradas",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = VagaDTO.class)))})
    })
    @GetMapping("filtro")
    @ResponseBody
    public List<VagaDTO> buscarPorFiltro(@RequestParam String filtro,
                                         @RequestParam final String valor) {
        List<Vaga> vagas;
        switch (filtro) {
            case "areaAtuacao":
                vagas = vagaService.buscarPorAreaAtuacao(valor);
                break;
            case "cargo":
                vagas = vagaService.buscarPorCargo(Cargo.valueOf(valor.toUpperCase(Locale.ROOT)));
                break;
            case "localidade":
                vagas = vagaService.buscarPorLocalidade(valor);
                break;
            default:
                throw new BadRequestException("Filtro invalido");
        }
        return VagaDTO.toDTOList(vagas);
    }

    @Operation(summary = "Altera uma vaga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vaga alterada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VagaDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Vaga nao encontrada", content = @Content)
    })
    @PutMapping("{id}")
    @ResponseBody
    public VagaDTO alterar(@PathVariable Long id,
                           @RequestBody VagaDTO vagaDTO) {
        return new VagaDTO(vagaService.alterar(id, vagaDTO.toEntity()));
    }

    @Operation(summary = "Remove uma vaga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vaga removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Vaga nao encontrada", content = @Content)
    })
    @DeleteMapping("{id}")
    @ResponseBody
    public String removerVaga(@PathVariable Long id) {
        vagaService.remover(id);
        return "Vaga removida com sucesso.";
    }

    @Operation(summary = "Lista todas as areas de atuacao cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Areas de atuacao encontradas",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class)))})
    })
    @GetMapping("area_atuacao")
    @ResponseBody
    public Map<String, List<String>> listarAreaAtuacao() {
        Map<String, List<String>> resp = new HashMap<>();
        resp.put("listaAreaAtuacao", vagaService.listarAreaAtuacao());
        return resp;
    }
}
