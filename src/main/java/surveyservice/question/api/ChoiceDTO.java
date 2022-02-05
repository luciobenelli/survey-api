package surveyservice.question.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import surveyservice.question.model.Choice;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChoiceDTO {

    private Long id;
    @NotNull
    private String description;

    public static ChoiceDTO toDTO(Choice choice) {
        return ChoiceDTO.builder()
                .id(choice.getId())
                .description(choice.getDescription())
                .build();
    }

    public static Choice toEntity(ChoiceDTO dto) {
        return Choice.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .build();
    }
}
