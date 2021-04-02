package br.uff.tecnomarias.rest.resource;

import br.uff.tecnomarias.domain.entity.Feedback;
import br.uff.tecnomarias.rest.dto.FeedbackDTO;
import br.uff.tecnomarias.service.FeedbackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("feedback")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FeedbackResource {

    @Inject
    FeedbackService feedbackService;

    @POST
    public Response salvar(FeedbackDTO feedbackDTO) {
        //TODO implementar auth
        return Response.ok(new FeedbackDTO(
                feedbackService.salvar(feedbackDTO.toEntity())))
                .build();
    }

    @GET
    public Response buscarRecentes() {
        List<Feedback> resp = feedbackService.buscarRecentes();
        return Response.ok(resp).build();
    }
}
