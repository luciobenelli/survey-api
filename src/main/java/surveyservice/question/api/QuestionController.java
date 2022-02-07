package surveyservice.question.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import surveyservice.question.service.QuestionService;

import javax.validation.Valid;
import java.net.URI;
import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping(path = "v1/surveys/{idSurvey}/questions")
@Api(tags = "Question CRUD methods")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all survey questions")
    List<QuestionDTO> getQuestions(@PathVariable(value = "idSurvey") Long idSurvey) {
        return questionService.getQuestions(idSurvey);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a specific survey question")
    QuestionDTO getQuestion(@PathVariable(value = "idSurvey") Long idSurvey, @PathVariable(value = "id") Long id) {
        return questionService.getQuestion(idSurvey, id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new question for a survey")
    ResponseEntity<Void> createQuestion(@PathVariable(value = "idSurvey") Long idSurvey, @RequestBody @Valid QuestionDTO questionDTO) {
        var id = questionService.createQuestion(idSurvey, questionDTO);
        var uri = MessageFormat.format("v1/surveys/{0}/questions/{1}", idSurvey, id);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a survey question")
    ResponseEntity<Void> updateQuestion(@PathVariable(value = "idSurvey") Long idSurvey,
                                        @PathVariable(value = "id") Long id,
                                        @RequestBody @Valid QuestionDTO questionDTO) {
        questionService.updateQuestion(idSurvey, questionDTO);
        var uri = MessageFormat.format("v1/surveys/{0}/questions/{1}", idSurvey, id);
        return ResponseEntity.ok()
                .header("location", URI.create(uri).toString())
                .build();
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a survey question")
    void deleteQuestion(@PathVariable(value = "idSurvey") Long idSurvey,
                        @PathVariable(value = "id") Long id) {
        questionService.deleteQuestion(idSurvey, id);
    }
}
