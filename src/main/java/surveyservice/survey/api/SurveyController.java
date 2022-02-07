package surveyservice.survey.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import surveyservice.survey.service.SurveyService;

import javax.validation.Valid;
import java.net.URI;
import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping(path = "v1/surveys")
@Api(tags = "Survey CRUD methods")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all surveys")
    List<SurveyDTO> getSurveys() {
        return surveyService.getSurveys();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a specific survey")
    SurveyDTO getSurvey(@PathVariable(value = "id") Long id) {
        return surveyService.getSurvey(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new survey")
    ResponseEntity<Void> createSurvey(@RequestBody @Valid SurveyDTO surveyDTO) {
        var id = surveyService.createSurvey(surveyDTO);
        var uri = MessageFormat.format("v1/surveys/{0}", id);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a survey")
    ResponseEntity<Void> updateSurvey(@PathVariable(value = "id") Long id,
                                        @RequestBody @Valid SurveyDTO surveyDTO) {
        surveyService.updateSurvey(surveyDTO);
        var uri = MessageFormat.format("v1/surveys/{0}", id);
        return ResponseEntity.ok()
                .header("location", URI.create(uri).toString())
                .build();
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a survey")
    void deleteSurvey(@PathVariable(value = "id") Long id) {
        surveyService.deleteSurvey(id);
    }
}
