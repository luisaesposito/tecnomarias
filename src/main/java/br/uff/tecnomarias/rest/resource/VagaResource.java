package br.uff.tecnomarias.rest.resource;

import br.uff.tecnomarias.service.VagaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("vaga")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VagaResource {

    @Inject
    VagaService vagaService;

    @GET
    @Path("empresa/{cnpj}")
    public Response buscarPorEmpresa(@PathParam("cnpj") final String cnpj) {
        return Response.ok(vagaService.buscarPorEmpresa(cnpj)).build();
    }
}
