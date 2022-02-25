package surveyservice.response.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import surveyservice.response.service.ResponseService;

import javax.validation.Valid;
import java.net.URI;
import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping("v1/surveys/{idSurvey}/responses")
@Api(tags = "Responses CR methods")
@RequiredArgsConstructor
public class ResponseController {

    private final ResponseService responseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all survey responses")
    List<ResponseDTO> getResponses(@PathVariable(value = "idSurvey") Long idSurvey) {
        return responseService.getResponses(idSurvey);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a survey responses")
    ResponseDTO getResponse(@PathVariable(value = "idSurvey")Long idSurvey, @PathVariable(value = "id") Long id) {
        return responseService.getResponse(idSurvey, id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new response for a survey")
    ResponseEntity<Void> createResponse(@PathVariable(value = "idSurvey") Long idSurvey, @RequestBody @Valid ResponseDTO responseDTO) {
        var id = responseService.createResponse(idSurvey, responseDTO);
        var uri = MessageFormat.format("v1/surveys/{0}/responses/{1}", idSurvey, id);
        return ResponseEntity.created(URI.create(uri)).build();
    }

}
