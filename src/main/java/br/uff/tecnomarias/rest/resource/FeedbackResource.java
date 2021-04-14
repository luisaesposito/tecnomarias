package br.uff.tecnomarias.rest.resource;

import br.uff.tecnomarias.rest.dto.FeedbackDTO;
import br.uff.tecnomarias.service.FeedbackService;
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

    @PostMapping
    @ResponseBody
    public FeedbackDTO salvar(FeedbackDTO feedbackDTO) {
        return new FeedbackDTO(feedbackService.salvar(feedbackDTO.toEntity()));
    }

    @GetMapping
    @ResponseBody
    public List<FeedbackDTO> buscarRecentes() {
        return FeedbackDTO.toDTOList(feedbackService.buscarRecentes());
    }
}
