package surveyservice.question.service;

import surveyservice.question.api.QuestionDTO;

import java.util.List;

public interface QuestionService {
    void deleteQuestion(Long idSurvey, Long id);

    List<QuestionDTO> getQuestions(Long idSurvey);

    QuestionDTO getQuestion(Long idSurvey, Long id);

    Long createQuestion(Long idSurvey, QuestionDTO questionDTO);

    void updateQuestion(Long idSurvey, QuestionDTO questionDTO);
}
