package br.uff.tecnomarias.rest.resource;


import br.uff.tecnomarias.rest.dto.PessoaFisicaDTO;
import br.uff.tecnomarias.service.PessoaFisicaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("pf")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaFisicaResource {

    @Inject
    PessoaFisicaService pfService;

    @GET
    public Response buscarTodas() {
        return Response.ok(PessoaFisicaDTO.toDTOList(pfService.buscarTodas())).build();
    }

    @GET
    @Path("{id}")
    public Response buscarPorId(@PathParam("id") final Long id) {
        return Response.ok(new PessoaFisicaDTO(pfService.buscarPorId(id))).build();
    }
}
