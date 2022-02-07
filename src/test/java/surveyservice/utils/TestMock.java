package surveyservice.utils;

import surveyservice.question.api.ChoiceDTO;
import surveyservice.question.api.QuestionDTO;
import surveyservice.question.model.Choice;
import surveyservice.question.model.Question;
import surveyservice.survey.api.SurveyDTO;
import surveyservice.survey.model.Survey;

import java.util.List;

public class TestMock {

    public static SurveyDTO getSurveyDTO() {
        return SurveyDTO.builder()
                .id(1L)
                .description("survey description")
                .name("survey name")
                .status(StatusEnum.OPEN)
                .build();
    }

    public static Survey getSurvey() {
        return Survey.builder()
                .id(1L)
                .name("survey name")
                .description("survey description")
                .status(StatusEnum.OPEN)
                .build();
    }

    public static QuestionDTO getQuestionDTO() {
        return QuestionDTO.builder()
                .id(1L)
                .title("question title")
                .choiceDTOList(getChoiceDTOList())
                .build();
    }

    private static List<ChoiceDTO> getChoiceDTOList() {
        return List.of(getChoiceDTO(1L), getChoiceDTO(2L));
    }

    private static ChoiceDTO getChoiceDTO(long id) {
        return ChoiceDTO.builder()
                .id(id)
                .description("choice "+ id)
                .build();
    }


    public static Question getQuestion() {
        return Question.builder()
                .id(1L)
                .title("question title")
                .survey(getSurvey())
                .choiceList(getChoiceList())
                .build();
    }

    private static List<Choice> getChoiceList() {
        return List.of(getChoice(1L), getChoice(2L));
    }

    private static Choice getChoice(Long id) {
        return Choice.builder()
                .id(id)
                .description("choice description " + id)
                .build();
    }
}
