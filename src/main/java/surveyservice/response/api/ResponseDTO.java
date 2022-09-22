package surveyservice.response.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import surveyservice.response.model.Answer;
import surveyservice.response.model.Response;
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
public class ResponseDTO {

    @NotNull
    private RespondentDTO respondentDTO;

    @NotEmpty
    private List<AnswerDTO> answerDTOList;

    public static ResponseDTO toDTO(Response response) {
        return ResponseDTO.builder()
                .respondentDTO(RespondentDTO.toDTO(response.getRespondent()))
                .answerDTOList(getAnswerDTOList(response))
                .build();
    }

    public static Response toEntity(Survey survey, ResponseDTO dto) {
        var respondent = RespondentDTO.toEntity(dto.getRespondentDTO());

        var newResponse = Response.builder()
                .respondent(respondent)
                .survey(survey)
                .build();
        newResponse.setAnswerList(getAnswerList(survey, dto));
        return newResponse;
    }

    private static List<Answer> getAnswerList(Survey survey, ResponseDTO dto) {
        return dto.getAnswerDTOList().stream()
                .map(answerDTO -> AnswerDTO.toEntity(survey, answerDTO))
                .collect(Collectors.toList());
    }

    private static List<AnswerDTO> getAnswerDTOList(Response response) {
        return response.getAnswerList().stream()
                .map(AnswerDTO::toDTO)
                .collect(Collectors.toList());
    }

}
