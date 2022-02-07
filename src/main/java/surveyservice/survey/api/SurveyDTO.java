package surveyservice.survey.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import surveyservice.survey.model.Survey;
import surveyservice.utils.StatusEnum;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurveyDTO {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private StatusEnum status;

    public static SurveyDTO toDTO(Survey survey) {
        return SurveyDTO.builder()
                .id(survey.getId())
                .name(survey.getName())
                .description(survey.getDescription())
                .status(survey.getStatus())
                .build();
    }

    public static Survey toEntity(SurveyDTO dto) {
        return Survey.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .build();
    }
}
