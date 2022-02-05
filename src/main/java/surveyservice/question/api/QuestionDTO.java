package surveyservice.question.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import surveyservice.question.model.Choice;
import surveyservice.question.model.Question;
import surveyservice.survey.model.Survey;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionDTO {

    private Long id;
    @NotNull
    private String title;
    @NotEmpty
    private List<ChoiceDTO> choiceDTOList;

    public static QuestionDTO toDTO(Question question) {
        return QuestionDTO.builder()
                .id(question.getId())
                .title(question.getTitle())
                .choiceDTOList(getChoiceDTOList(question))
                .build();
    }

    private static List<ChoiceDTO> getChoiceDTOList(Question question) {
        return question.getChoiceList().stream()
                .map(ChoiceDTO::toDTO)
                .collect(Collectors.toList());
    }

    public static Question toEntity(Survey survey, QuestionDTO dto) {
        return Question.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .survey(survey)
                .choiceList(getChoiceList(dto))
                .build();
    }

    private static List<Choice> getChoiceList(QuestionDTO dto) {
        return dto.getChoiceDTOList().stream()
                .map(ChoiceDTO::toEntity)
                .collect(Collectors.toList());
    }
}
