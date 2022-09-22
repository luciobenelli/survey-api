package surveyservice.utils;

import surveyservice.question.api.ChoiceDTO;
import surveyservice.question.api.QuestionDTO;
import surveyservice.question.model.Choice;
import surveyservice.question.model.Question;
import surveyservice.response.api.AnswerDTO;
import surveyservice.response.api.RespondentDTO;
import surveyservice.response.api.ResponseDTO;
import surveyservice.response.model.Answer;
import surveyservice.response.model.Respondent;
import surveyservice.response.model.Response;
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
                .description("choice description "+ id)
                .build();
    }

    public static List<Question> getQuestionList() {
        return List.of(getQuestion(1L), getQuestion(2L));
    }

    public static Question getQuestion() {
        return getQuestion(1L);
    }

    public static Question getQuestion(Long questionId) {
        return Question.builder()
                .id(questionId)
                .title("question title")
                .survey(getSurvey())
                .choiceList(getChoiceList(questionId))
                .build();
    }

    private static List<Choice> getChoiceList(Long fator) {
        var id1 = 2L * fator - 1L;
        var id2 = 2L * fator;

        return List.of(getChoice(id1), getChoice(id2));
    }

    private static Choice getChoice(Long id) {
        return Choice.builder()
                .id(id)
                .description("choice description " + id)
                .build();
    }

    public static ResponseDTO getResponseDTO() {
        return ResponseDTO.builder()
                .answerDTOList(getAnswerDTOList())
                .respondentDTO(getRespondentDTO())
                .build();
    }

    private static RespondentDTO getRespondentDTO() {
        return RespondentDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@johndoe.com")
                .build();
    }

    private static List<AnswerDTO> getAnswerDTOList() {
        return List.of(getAnswerDTO(1L, 1L), getAnswerDTO(2L, 3L));
    }

    public static AnswerDTO getAnswerDTO(Long questionId, Long choiceId){
        return AnswerDTO.builder()
                .questionId(questionId)
                .choiceId(choiceId)
                .build();
    }

    public static Response getResponse() {
        return Response.builder()
                .id(1L)
                .survey(getSurvey())
                .respondent(getRespondent())
                .answerList(getAnswerList())
                .build();
    }

    private static Respondent getRespondent() {
        return Respondent.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@johndoe.com")
                .build();
    }

    private static List<Answer> getAnswerList() {
        return List.of(getAnswer(1L, 1L), getAnswer(2L, 3L));
    }

    private static Answer getAnswer(Long questionId, Long choiceId){
        return Answer.builder()
                .id(questionId)
                .question(getQuestion(questionId))
                .choice(getChoice(choiceId))
                .build();
    }
}
