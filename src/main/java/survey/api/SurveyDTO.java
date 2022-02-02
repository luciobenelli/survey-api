package survey.api;

import lombok.*;

@Getter
@Setter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SurveyDTO {

    private Long id;

    public static SurveyDTO build(Long id) {
        return SurveyDTO.builder()
                .id(id)
                .build();
    }

}
