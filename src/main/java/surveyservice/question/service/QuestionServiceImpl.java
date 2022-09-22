package surveyservice.question.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import surveyservice.question.api.QuestionDTO;
import surveyservice.question.model.Question;
import surveyservice.question.repository.QuestionRepository;
import surveyservice.survey.repository.SurveyRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;

    @Override
    public void deleteQuestion(Long idSurvey, Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<QuestionDTO> getQuestions(Long idSurvey) {
        return questionRepository.findAllBySurvey_Id(idSurvey)
                .stream()
                .map(QuestionDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public QuestionDTO getQuestion(Long idSurvey, Long id) {
        return questionRepository.findBySurvey_IdAndId(idSurvey, id)
                .map(QuestionDTO::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Question " + id + " not found for survey " + idSurvey));
    }

    @Override
    public Long createQuestion(Long idSurvey, QuestionDTO questionDTO) {
        var question = surveyRepository.findById(idSurvey)
                .map(survey -> QuestionDTO.toEntity(survey, Question.builder().build(), questionDTO))
                .orElseThrow(() -> new EntityNotFoundException("Survey " + idSurvey + " not found"));
        return questionRepository.save(question).getId();
    }

    @Override
    public void updateQuestion(Long idSurvey, QuestionDTO questionDTO) {
        var updatedQuestion = questionRepository.findBySurvey_IdAndId(idSurvey, questionDTO.getId())
                .map(question -> QuestionDTO.toEntity(question.getSurvey(), question, questionDTO))
                .orElseThrow(() -> new EntityNotFoundException("Question " + questionDTO.getId() + " not found for survey " + idSurvey));
        questionRepository.save(updatedQuestion);
    }
}
