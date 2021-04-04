package br.uff.tecnomarias.rest.resource;

import br.uff.tecnomarias.domain.entity.Vaga;
import br.uff.tecnomarias.rest.dto.VagaDTO;
import br.uff.tecnomarias.service.VagaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("vaga")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VagaResource {

    @Inject
    VagaService vagaService;

    @POST
    public Response criarVaga(VagaDTO vagaDTO) {
        VagaDTO vagaSalva = new VagaDTO(vagaService.salvar(vagaDTO.toEntity()));
        return Response.status(Response.Status.CREATED).entity(vagaSalva).build();
    }

    @GET
    public Response buscarTodas(@QueryParam("start") final int start,
                                @QueryParam("pageSize") final int size) {
        List<Vaga> vagas = vagaService.buscarTodas(start, size);
        return Response.ok(vagas.stream().map(vaga -> new VagaDTO(vaga)).collect(Collectors.toList())).build();
    }

    @GET
    @Path("{id}")
    public Response buscarPorId(@PathParam("id") final Long id) {
        Vaga vaga = vagaService.buscarPorId(id);
        Response.Status status = vaga != null ? Response.Status.OK : Response.Status.NO_CONTENT;
        return Response.status(status).entity(vaga).build();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response buscarTotal() {
        return Response.ok(vagaService.count()).build();
    }

    @GET
    @Path("empresa/{id}")
    public Response buscarPorEmpresa(@PathParam("id") final Long id) {
        return Response.ok(vagaService.buscarPorEmpresa(id)).build();
    }

    @GET
    @Path("filtro")
    public Response buscarPorFiltro(@QueryParam("filtro") final String filtro,
                                    @QueryParam("valor") final String valor) {
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
                return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(VagaDTO.toDTOList(vagas)).build();
    }

    @PUT
    @Path("{id}")
    public Response alterar(@PathParam("id") final Long id,
                            final VagaDTO vagaDTO) {
        return Response.ok(new VagaDTO(
                vagaService.alterar(id, vagaDTO.toEntity())
        )).build();
    }

    @DELETE
    @Path("{id}")
    public Response removerVaga(@PathParam("id") final Long id) {
        vagaService.remover(id);
        return Response.ok("Vaga removida com sucesso.").build();
    }

    @GET
    @Path("area_atuacao")
    public Response listarAreaAtuacao() {
        Map<String, List<String>> resp = new HashMap<>();
        resp.put("listaAreaAtuacao", vagaService.listarAreaAtuacao());
        return Response.ok(resp).build();
    }
}
