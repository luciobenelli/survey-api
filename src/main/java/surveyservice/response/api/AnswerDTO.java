package surveyservice.response.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import surveyservice.question.model.Choice;
import surveyservice.question.model.Question;
import surveyservice.response.model.Answer;

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

    public static Answer toEntity(AnswerDTO dto) {
        var choice = Choice.builder()
                .id(dto.getChoiceId())
                .build();

        var question = Question.builder()
                .id(dto.getQuestionId())
                .build();

        return Answer.builder()
                .choice(choice)
                .question(question)
                .build();
    }

    public static AnswerDTO toDTO(Answer answer) {
        return AnswerDTO.builder()
                .choiceId(answer.getChoice().getId())
                .questionId(answer.getQuestion().getId())
                .build();
    }
}
