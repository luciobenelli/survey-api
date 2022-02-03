package surveyservice.utils;

import surveyservice.survey.api.SurveyDTO;
import surveyservice.survey.model.Survey;

public class TestMock {

    public static SurveyDTO getSurveyDTO() {
        return SurveyDTO.builder()
                .id(1L)
                .description("my survey description")
                .name("my survey name")
                .status(StatusEnum.OPEN)
                .build();
    }

    public static Survey getSurvey() {
        return Survey.builder()
                .id(2L)
                .name("my other survey")
                .description("my other survey description")
                .status(StatusEnum.CLOSED)
                .build();
    }
}
