package br.uff.tecnomarias.rest.resource;

import br.uff.tecnomarias.rest.dto.FeedbackDTO;
import br.uff.tecnomarias.service.FeedbackService;
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
@RequestMapping("feedback")
@Tag(name = "Feedback")
@CrossOrigin
public class FeedbackResource {

    @Autowired
    FeedbackService feedbackService;

    @Operation(summary = "Lista os 3 feedbacks mais recentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de feedbacks",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FeedbackDTO.class)))})
    })
    @GetMapping
    @ResponseBody
    public List<FeedbackDTO> buscarRecentes() {
        return FeedbackDTO.toDTOList(feedbackService.buscarRecentes());
    }
}
