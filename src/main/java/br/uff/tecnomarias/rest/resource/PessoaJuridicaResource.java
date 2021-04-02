package br.uff.tecnomarias.rest.resource;

import br.uff.tecnomarias.rest.dto.AvaliacaoDTO;
import br.uff.tecnomarias.rest.dto.PessoaJuridicaDTO;
import br.uff.tecnomarias.service.PessoaJuridicaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("pj")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaJuridicaResource {

    @Inject
    PessoaJuridicaService pjService;

    @GET
    public Response buscarTodas() {
        return Response.ok(PessoaJuridicaDTO.toDTOList(pjService.buscarTodas())).build();
    }

    @GET
    @Path("{id}")
    public Response buscarPorId(@PathParam("id") final Long id) {
        return Response.ok(new PessoaJuridicaDTO(pjService.buscarPorId(id))).build();
    }

    @POST
    @Path("{id}/avaliacao")
    public Response avaliarEmpresa(@PathParam("id") final Long id, AvaliacaoDTO avaliacaoDTO) {
        AvaliacaoDTO resp = new AvaliacaoDTO(pjService.avaliarEmpresa(id, avaliacaoDTO.toEntity()));
        return Response.ok(resp).build();
    }

}
