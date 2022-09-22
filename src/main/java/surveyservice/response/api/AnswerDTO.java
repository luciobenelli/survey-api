package surveyservice.response.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import surveyservice.question.model.Choice;
import surveyservice.question.model.Question;
import surveyservice.response.model.Answer;
import surveyservice.survey.model.Survey;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerDTO {

    @NotNull
    private Long questionId;

    @NotNull
    private Long choiceId;

    public static Answer toEntity(Survey survey, AnswerDTO dto) {
        var question = getQuestion(survey, dto);

        var choice = getChoice(dto, question);

        return Answer.builder()
                .question(question)
                .choice(choice)
                .build();
    }

    private static Choice getChoice(AnswerDTO dto, Question question) {
        return question.getChoiceList().stream()
                .filter(c1 -> dto.choiceId.equals(c1.getId()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Choice " + dto.getChoiceId() + " not found for question " + dto.getQuestionId()));
    }

    private static Question getQuestion(Survey survey, AnswerDTO dto) {
        return survey.getQuestionList().stream()
                .filter(q1 -> dto.questionId.equals(q1.getId()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Question " + dto.getQuestionId() + " not found for survey " + survey.getId()));
    }

    public static AnswerDTO toDTO(Answer answer) {
        return AnswerDTO.builder()
                .choiceId(answer.getChoice().getId())
                .questionId(answer.getQuestion().getId())
                .build();
    }
}
