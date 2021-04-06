package br.uff.tecnomarias.rest.resource;


import br.uff.tecnomarias.rest.dto.PessoaFisicaDTO;
import br.uff.tecnomarias.rest.dto.PessoaJuridicaDTO;
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

    @POST
    public Response salvarPF(PessoaFisicaDTO pfDTO) {
        PessoaFisicaDTO pfSalva = new PessoaFisicaDTO(pfService.salvar(pfDTO.toEntity()));
        return Response.ok(pfSalva).build();
    }

    @PUT
    @Path("{id}")
    public Response alterar(@PathParam("id") final Long id, PessoaFisicaDTO pfDTO) {
        return Response.ok(new PessoaFisicaDTO(pfService.alterar(id, pfDTO.toEntity()))).build();
    }

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
