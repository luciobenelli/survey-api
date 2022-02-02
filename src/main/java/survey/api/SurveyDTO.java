package survey.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import survey.utils.StatusEnum;

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
}
