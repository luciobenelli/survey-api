package survey.utils;

import survey.api.SurveyDTO;

public class TestMock {

    public static SurveyDTO getSurveyDTO() {
        return SurveyDTO.builder()
                .id(1L)
                .description("my survey description")
                .name("my survey name")
                .status(StatusEnum.OPEN)
                .build();
    }
}
