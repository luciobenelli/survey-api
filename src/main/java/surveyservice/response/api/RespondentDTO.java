package surveyservice.response.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import surveyservice.response.model.Respondent;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RespondentDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    public static Respondent toEntity(RespondentDTO dto) {
        return Respondent.builder()
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
    }

    public static RespondentDTO toDTO(Respondent respondent) {
        return RespondentDTO.builder()
                .firstName(respondent.getFirstName())
                .lastName(respondent.getLastName())
                .email(respondent.getEmail())
                .build();
    }
}
