package br.uff.tecnomarias.rest.resource;

import br.uff.tecnomarias.domain.entity.Vaga;
import br.uff.tecnomarias.rest.dto.VagaDTO;
import br.uff.tecnomarias.service.VagaService;
import br.uff.tecnomarias.service.exception.BadRequestException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("vaga")
@Tag(name = "Vaga")
@CrossOrigin
public class VagaResource {

    @Autowired
    VagaService vagaService;

    @PostMapping
    @ResponseBody
    public VagaDTO criarVaga(@RequestBody VagaDTO vagaDTO) {
        return new VagaDTO(vagaService.salvar(vagaDTO.toEntity()));
    }

    @GetMapping
    @ResponseBody
    public List<VagaDTO> buscarTodas() {
        List<Vaga> vagas = vagaService.buscarTodas();//start, pageSize);
        return vagas.stream().map(vaga -> new VagaDTO(vaga)).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    @ResponseBody
    public VagaDTO buscarPorId(@PathVariable Long id) {
        return new VagaDTO(vagaService.buscarPorId(id));
    }

    @GetMapping("count")
    @ResponseBody
    public Long buscarTotal() {
        return vagaService.count();
    }

    @GetMapping("empresa/{id}")
    @ResponseBody
    public List<VagaDTO> buscarPorEmpresa(@PathVariable Long id) {
        List<Vaga> vagas = vagaService.buscarPorEmpresa(id);
        return VagaDTO.toDTOList(vagas);
    }

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
                vagas = vagaService.buscarPorCargo(valor);
                break;
            case "localidade":
                vagas = vagaService.buscarPorLocalidade(valor);
                break;
            default:
                throw new BadRequestException("Filtro invalido");
        }
        return VagaDTO.toDTOList(vagas);
    }

    @PutMapping("{id}")
    @ResponseBody
    public VagaDTO alterar(@PathVariable Long id,
                           @RequestBody VagaDTO vagaDTO) {
        return new VagaDTO(vagaService.alterar(id, vagaDTO.toEntity()));
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public String removerVaga(@PathVariable Long id) {
        vagaService.remover(id);
        return "Vaga removida com sucesso.";
    }

    @GetMapping("area_atuacao")
    @ResponseBody
    public Map<String, List<String>> listarAreaAtuacao() {
        Map<String, List<String>> resp = new HashMap<>();
        resp.put("listaAreaAtuacao", vagaService.listarAreaAtuacao());
        return resp;
    }
}
